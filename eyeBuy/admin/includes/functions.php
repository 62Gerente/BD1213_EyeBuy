<?php

class adminpanel {

  var $hostname;
  var $username;
  var $password;
  var $charset;

// Contrutores

  public function __construct($host,$user,$password,$charset){
    $this->hostname=$host;
    $this->username=$user;
    $this->password=$password;
    $this->charset=$charset;
  }

//  Conexão    

  public function connect() {
    $conn = oci_connect($this->username,$this->password,$this->hostname,$this->charset) or die("Falhou a conexão à base de dados!");
    return $conn;
  }

// Autenticação e sessões

  public function authcheck(){
    session_start();
    if(isset($_SESSION['user'])){}
    else {session_destroy(); header("location:login.php");}
  }

    public function cleansessions(){
    session_start();
    session_destroy();
  }

  public function login($conn){ 
    if(isset($_POST['username']) && isset($_POST['password']) && $_POST['password']!=null && $_POST['username']!=null){
        $uname=mysql_real_escape_string($_POST['username']);
        $pw=mysql_real_escape_string($_POST['password']);

        $admqr = oci_parse($conn, "SELECT * FROM Administradores WHERE login='$uname' and password=md5('$pw')");
        oci_execute($admqr);
        $rows=array();
        oci_fetch_all($admqr,$res);
        $count=oci_num_rows($admqr);
        oci_free_statement($admqr);
        if($count==1) { session_start(); $_SESSION['user']="$uname"; header("location:dashboard.php");}
      }
    }

  public function wronglogin(){
    if(isset($_POST['username']) or isset($_POST['password'])){
      echo '<script type="text/javascript">'."$.gritter.add({
      title:  'Não foi possível efectuar login!',
      text: 'Verifique os seus dados e tente novamente',
      sticky: false
    });</script>";
    }
  }


  public function logout(){
    session_start();
    if(isset($_SESSION['user'])){
      unset($_SESSION['user']);
      session_destroy();
      header("location:login.php");
    }
  }


// Funções auxiliares

  public function contarows($conn, $sql){
    $execute=oci_parse($conn, $sql);
    oci_execute($execute);
    oci_fetch($execute);
    $num=oci_result($execute, 1);
    oci_free_statement($execute);
    return $num;
  }


// Messages Functions

  public function msgnr($conn)
  {
    $uname=$_SESSION['user'];
    $nr = $this->contarows($conn, "SELECT count(*) FROM Mensagens where destino='$uname' and lida=0 and (tipo='Mensagem Pessoal' or tipo='Mensagem Administrador')");
    return $nr;
  }


    public function ticketnr($conn)
  {
    $nr = $this->contarows($conn, "SELECT count(*) FROM Mensagens where lida=0 and tipo='Mensagem Administrador'");
    return $nr;
  }    

  public function ticketnotsolved($conn)
  {
    $nr = $this->contarows($conn, "SELECT count(*) FROM Mensagens where lida=1 and apagadadt=0 and tipo='Mensagem Administrador'");
    return $nr;
  }

  public function msglist($conn)
  {
    $uname=$_SESSION['user'];
    $msgqr = oci_parse($conn, "SELECT * FROM Mensagens where destino='$uname' and (tipo='Mensagem Pessoal' or tipo='Mensagem Administrador') ORDER BY id DESC");
    
    oci_execute($msgqr);
    
    while(oci_fetch($msgqr)){
      $msgtime = oci_parse($conn, "SELECT EXTRACT(year FROM data) Ano, EXTRACT(month FROM data) MES, EXTRACT(day FROM data) DIA, EXTRACT(hour FROM data) H, EXTRACT(minute FROM data) M FROM Mensagens where id=".oci_result($msgqr, 1));
      oci_execute($msgtime);
      oci_fetch($msgtime);
      if(oci_result($msgqr, 10)==0){
        $str=oci_result($msgtime, 3).'-'.oci_result($msgtime, 2).'-'.oci_result($msgtime, 1).' '.oci_result($msgtime, 4).':'.oci_result($msgtime, 5);
        $timestr=date('d M Y \à\s H:i',strtotime($str));
        echo '<tr>';
        echo '<td style="width: 0%;display:none;">'.(0-(strtotime($str))).'</td>';
        if((oci_result($msgqr,8))==0){echo '<td><a href="message.php?id='.oci_result($msgqr,1).'"><span class="label label-important">new</span><b> '.oci_result($msgqr,5).'</b></a></td>';}
        else{echo '<td><a href="message.php?id='.oci_result($msgqr,1).'">'.oci_result($msgqr,5).'</a></td>';}
        echo '<td><a href="profile.php?user='.oci_result($msgqr,2).'">  '.oci_result($msgqr,2).'</a></td>';
        echo '<td style="text-align: center;"><div style="width: 0%;display:none;">'.(0-(strtotime($str))).'</div>'.$timestr.'</td>';
        echo '<td><div style="display:none;">'.oci_result($msgqr,6).'</div><center><a href="message.php?id='.oci_result($msgqr,1).'&open=1"><button class="btn btn-primary btn-mini"><i class="icon-pencil icon-white"></i> Responder</button></a> </div><a href="message.php?id='.oci_result($msgqr,1).'&del=1"><button class="btn btn-danger btn-mini"><i class="icon-remove icon-white"></i> Apagar</button></a></center></td>';
        echo '</tr>';
      }
      oci_free_statement($msgtime);

    }
    oci_free_statement($msgqr);
    
  }



    public function ticketlist($conn)
  {
    $uname=$_SESSION['user'];
    $msgqr = oci_parse($conn, "SELECT * FROM Mensagens where tipo='Mensagem Administrador' ORDER BY id DESC");
    
    oci_execute($msgqr);
    
    while(oci_fetch($msgqr)){
      $msgtime = oci_parse($conn, "SELECT EXTRACT(year FROM data) Ano, EXTRACT(month FROM data) MES, EXTRACT(day FROM data) DIA, EXTRACT(hour FROM data) H, EXTRACT(minute FROM data) M FROM Mensagens where id=".oci_result($msgqr, 1));
      oci_execute($msgtime);
      oci_fetch($msgtime);
        $str=oci_result($msgtime, 3).'-'.oci_result($msgtime, 2).'-'.oci_result($msgtime, 1).' '.oci_result($msgtime, 4).':'.oci_result($msgtime, 5);
        $timestr=date('d M Y \à\s H:i',strtotime($str));
        echo '<tr>';
        echo '<td style="width: 0%;display:none;">'.(0-(strtotime($str))).'</td>';
        if((oci_result($msgqr,10))==-1){echo '<td><a href="message.php?id='.oci_result($msgqr,1).'"><span class="label label-success">Resolvida</span> '.oci_result($msgqr,5).'</a></td>';}
        elseif((oci_result($msgqr,8))==0){echo '<td><a href="message.php?id='.oci_result($msgqr,1).'"><span class="label label-important">Novo</span><b> '.oci_result($msgqr,5).'</b></a></td>';}
        elseif(oci_result($msgqr, 8)==1){echo '<td><a href="message.php?id='.oci_result($msgqr,1).'"><span class="label label-warning">Em tratamento</span> '.oci_result($msgqr,5).'</a></td>';}
        else{echo '<td><a href="message.php?id='.oci_result($msgqr,1).'">'.oci_result($msgqr,5).'</a></td>';}
        echo '<td><a href="profile.php?user='.oci_result($msgqr,2).'">  '.oci_result($msgqr,2).'</a></td>';
        echo '<td style="text-align: center;"><div style="width: 0%;display:none;">'.(0-(strtotime($str))).'</div>'.$timestr.'</td>';
        echo '<td><div style="display:none;">'.oci_result($msgqr,6).'</div><center><a href="message.php?id='.oci_result($msgqr,1).'&open=1"><button class="btn btn-primary btn-mini"><i class="icon-pencil icon-white"></i> Responder</button></a> </div><a href="message.php?id='.oci_result($msgqr,1).'&solve=1"> <button class="btn btn-success btn-mini"><i class="icon-ok icon-white"></i> Resolvido</button></a></center></td>';
        echo '</tr>';
      oci_free_statement($msgtime);

    }
    oci_free_statement($msgqr);
    
  }


  public function msglistsent($conn)
  {
    $uname=$_SESSION['user'];
    $msgqr = oci_parse($conn, "SELECT * FROM Mensagens where origem='$uname' and (tipo='Mensagem Pessoal' or tipo='Mensagem Administrador') ORDER BY id DESC");
    
    oci_execute($msgqr);

    while(oci_fetch($msgqr) ){
      $msgtime = oci_parse($conn, "SELECT EXTRACT(year FROM data) Ano, EXTRACT(month FROM data) MES, EXTRACT(day FROM data) DIA, EXTRACT(hour FROM data) H, EXTRACT(minute FROM data) M FROM Mensagens where id=".oci_result($msgqr, 1));
      oci_execute($msgtime);
      oci_fetch($msgtime);
      if(oci_result($msgqr, 9)==0){
        $str=oci_result($msgtime, 3).'-'.oci_result($msgtime, 2).'-'.oci_result($msgtime, 1).' '.oci_result($msgtime, 4).':'.oci_result($msgtime, 5);
        $timestr=date('d M Y \à\s H:i',strtotime($str));
        echo '<tr class="gradeA odd">';
        echo '<td style="width: 0%;display:none;">'.(0-(strtotime($str))).'</td>';
        echo '<td><a href="mymessage.php?id='.oci_result($msgqr,1).'">'.oci_result($msgqr,5).'</a></td>';
        echo '<td>'.oci_result($msgqr,3).'</td>';
        echo '<td><div style="width: 0%;display:none;">'.(0-(strtotime($str))).'</div>'.$timestr.'</td>';
        echo '<td><center><a href="mymessage.php?id='.oci_result($msgqr,1).'&del=1"><button class="btn btn-danger btn-mini"><i class="icon-remove icon-white"></i> Apagar</button></a></center></td>';
        echo '</tr>';
      }
      oci_free_statement($msgtime);
    }
    oci_free_statement($msgqr);
  }


  public function getmsg($conn){
    $uname=$_SESSION['user'];
    $msgid=$_GET['id'];
    $msgqr = oci_parse($conn, "SELECT * FROM Mensagens where id=$msgid");
    $msgtime = oci_parse($conn, "SELECT EXTRACT(year FROM data) Ano, EXTRACT(month FROM data) MES, EXTRACT(day FROM data) DIA, EXTRACT(hour FROM data) H, EXTRACT(minute FROM data) M FROM Mensagens where id=$msgid");
    oci_execute($msgqr);
    oci_execute($msgtime);
    oci_fetch($msgqr);
    oci_fetch($msgtime);
    $str=oci_result($msgtime, 3).'-'.oci_result($msgtime, 2).'-'.oci_result($msgtime, 1).' '.oci_result($msgtime, 4).':'.oci_result($msgtime, 5);
    $timestr=date('d M Y \à\s H:i',strtotime($str));
    $res= array('id' => oci_result($msgqr,1) , 
                'origem' => oci_result($msgqr, 2), 
                'destino' => oci_result($msgqr, 3),
                'assunto' => oci_result($msgqr, 5), 
                'mensagem' => oci_result($msgqr, 6),
                'time' => $timestr);
    if(true){
    $read=oci_parse($conn, "UPDATE mensagens SET lida = 1 where id = $msgid");
    oci_execute($read);
    oci_free_statement($msgqr);
    oci_free_statement($read);
    oci_free_statement($msgtime);
    return $res;}
    else {
      oci_free_statement($msgqr);
      oci_free_statement($read);
      oci_free_statement($msgtime);
      header("location:inbox.php");}
  }





  public function markunread($conn){
    if(isset($_POST['unread'])){
      $msgidur=$_GET['id'];
      $unread=oci_parse($conn, "UPDATE mensagens SET lida = 0 where id =$msgidur");
      oci_execute($unread);
      oci_free_statement($unread);
      unset($_POST['unread']);
      header("location:inbox.php");
    }
  }

    public function markallread($conn){
    if(isset($_POST['allread'])){
      $user=$_SESSION['user'];
      $msgqr=oci_parse($conn, "UPDATE Mensagens set lida=1 where destino='$user'");
      oci_execute($msgqr);
      oci_free_statement($msgqr);
      echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> Todas as mensagens foram marcadas como lidas.
               </div>';
    }
  }

      public function markallsolved($conn){
    if(isset($_POST['allread'])){
      $user=$_SESSION['user'];
      $msgqr=oci_parse($conn, "UPDATE Mensagens set lida=1, apagadadt=-1 where tipo='Mensagem Administrador'");
      oci_execute($msgqr);
      oci_free_statement($msgqr);
      echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> Todas as notificações foram marcadas como resolvidas.
               </div>';
    }
  }


  public function deleteall($conn){
    if(isset($_POST['deleteall'])){
      $user=$_SESSION['user'];
      $box=$_POST['deleteall'];
      if($box==1){$msgqr=oci_parse($conn, "UPDATE Mensagens set apagadadt=1 where destino='$user'");}
      else {$msgqr=oci_parse($conn, "UPDATE Mensagens set apagadaor=1 where origem='$user'");}
      oci_execute($msgqr);
      oci_free_statement($msgqr);
      echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> Todas as mensagens foram apagadas com sucesso.
               </div>';
    }
  }


  public function replyopen(){
    if(isset($_GET['open'])){
      if($_GET['open']==1){echo 'class="in collapse" id="collapseTwo" style="height: auto;"';}
      else{echo 'class="collapse" id="collapseTwo" style="height: 0px;"';}
    }
    else{echo 'class="collapse" id="collapseTwo" style="height: 0px;"';}
  }

  public function replymsg($conn,$dest){
    if(isset($_POST['assunto']) && isset($_POST['mensagem'])){
    $user=$_SESSION['user'];
    $assunto=$_POST['assunto'];
    $msg=$_POST['mensagem'];
    $id=$_GET['id'];
    $msgins=oci_parse($conn, "INSERT INTO Mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
                                                    (mensagens_id.nextval,'$user','$dest','Mensagem Pessoal','$assunto','$msg',CURRENT_TIMESTAMP, 0, 0, 0)");
    oci_execute($msgins);
    oci_free_statement($msgins);
    header("location:message.php?id=$id&r=1");
    }
  }


  public function deletemsg($conn){
    if(isset($_POST['delete']) or isset($_GET['del'])){
      $id=$_GET['id'];
      $user=$_SESSION['user'];
      $msgqr=oci_parse($conn, "SELECT * FROM mensagens where id=$id");
      $apagaor=oci_parse($conn, "UPDATE mensagens SET apagadaor = 1 where id =$id");
      $apagadt=oci_parse($conn, "UPDATE mensagens SET apagadadt = 1 where id =$id");
      oci_execute($msgqr);
      oci_fetch($msgqr);
      $useror=oci_result($msgqr, 2);
      if($useror == $user){
        oci_execute($apagaor);
      }
      else {
        oci_execute($apagadt);
      }
      oci_free_statement($msgqr);
      oci_free_statement($apagaor);
      oci_free_statement($apagadt);
      unset($_POST['delete']);
      if($useror!=$user){header("location:inbox.php");}
      else {header("location:outbox.php");}
    }
    if(isset($_GET['solve'])){
      $id=$_GET['id'];
      $msgqr=oci_parse($conn, "UPDATE mensagens SET apagadadt = (-1) where id=$id");
      oci_execute($msgqr);
      oci_fetch($msgqr);
      oci_free_statement($msgqr);
      header("location:tickets.php");
    }
  }

    public function msgenc($conn){
    if(isset($_POST['assunto']) && isset($_POST['mensagem']) && isset($_POST['user']) && $_POST['user']!=null && $_POST['assunto']!=null && $_POST['mensagem']!=null ){
    $userfrom=$_SESSION['user'];
    $userto=$_POST['user'];
    $assunto=$_POST['assunto'];
    $msg=$_POST['mensagem'];
    $id=$_GET['id'];
    foreach ($userfrom as $value) {
      $msgins=oci_parse($conn, "INSERT INTO Mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
                                                    (mensagens_id.nextval,'$userfrom','$value','Mensagem Pessoal','$assunto','$msg',CURRENT_TIMESTAMP, 0, 0, 0)");
      oci_execute($msgins);
      oci_free_statement($msgins);
    }
    }
  }

  public function newreply($conn,$dest)
  {
    if(isset($_POST['newreply'])){
    $id=$_GET['id'];
    unset($_POST['newreply']);
    header("location:newmessage.php?id=$id&sendto=$dest");
    }
  }

  public function newmessage($conn){
    if(isset($_POST['user']) && isset($_POST['assunto']) && isset($_POST['mensagem']) && $_POST['assunto']!=null && $_POST['user']!=null && $_POST['mensagem']!=null){
      $userfrom=$_SESSION['user'];
      $userto=array();
      $userto=$_POST['user'];
      $ass=$_POST['assunto'];
      $msg=$_POST['mensagem'];
      $sent=0;
      foreach ($userto as $name){
        if($name=="Todos"){
          $sent=1;
          $todos=oci_parse($conn, "SELECT nomeutilizador from utilizadores");
          oci_execute($todos);
          while (oci_fetch($todos)) {
            $name=oci_result($todos, 1);
            $msgqr=oci_parse($conn, "INSERT INTO Mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
                                                      (mensagens_id.nextval,'$userfrom','$name','Mensagem Pessoal','$ass','$msg',CURRENT_TIMESTAMP, 0, 0, 0)");
            oci_execute($msgqr);
            oci_free_statement($msgqr);
          }
        }
      }
      if($sent==0){
        foreach ($userto as $name){
          $msgqr=oci_parse($conn, "INSERT INTO Mensagens (id,origem,destino,tipo,assunto,corpo,data,lida,apagadaor,apagadadt) VALUES 
                                                        (mensagens_id.nextval,'$userfrom','$name','Mensagem Pessoal','$ass','$msg',CURRENT_TIMESTAMP, 0, 0, 0)");
          oci_execute($msgqr);
          oci_free_statement($msgqr);
        }
      }
      echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> Mensagem enviada com sucesso! 
               </div>';
    }
    elseif(isset($_POST['submit'])){
      echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> Deixou campos por preencher, por favor preencha os campos e tente enviar novamente.
             </div>';
    }
  }


  public function usersmsg($conn){
    if(isset($_GET['sendto'])){ $user = $_GET['sendto'];
    $users=oci_parse($conn, "SELECT nomeutilizador from utilizadores where nomeutilizador!='$user'");}
    else {$users=oci_parse($conn, "SELECT nomeutilizador from utilizadores");}
    oci_execute($users);
    while(oci_fetch($users)){
      echo '<option>'.oci_result($users, 1).'</option>';
    }
    oci_free_statement($users);
  }


// Funções de utilizadores





  public function getusers($conn){
    $usrqr=oci_parse($conn, "SELECT nomeutilizador,nome,email,avaliacaocomprador,avaliacaovendedor,imagem,apagado FROM Utilizadores");
    oci_execute($usrqr);
    $ban='<span class="label label-important">Banido</span>';
    while(oci_fetch($usrqr)){
      $user=oci_result($usrqr, 1);
      $admin=$this->contarows($conn, "SELECT count(*) from administradores where login='$user'");
      if($admin==1){$stradmin='  <span class="label label-success"> Admin</span>';} else{$stradmin="";}
      $comprador=oci_result($usrqr, 4);
      $vendedor=oci_result($usrqr, 5);
      if(oci_result($usrqr, 6) != null){
        $img = oci_result($usrqr, 6)->load();
        $strimg='<img style="height:20px;width:20px;"  src="data:image/png;base64,'.base64_encode($img).'" />'; 
      }
      else {
        $strimg='<img style="height:20px;width:20px;"  src="img/user.png" />'; 
      }
              $ban='<span class="label label-important">Banido</span>';
      echo '<tr>';
            if(oci_result($usrqr, 7)==1){echo '<td><b><a href="profile.php?user='.oci_result($usrqr, 1).'">'.$strimg.' '.oci_result($usrqr, 1).' </a></b>'.$stradmin." ".$ban.'</td>';}
                 else{ echo '<td><b><a href="profile.php?user='.oci_result($usrqr, 1).'">'.$strimg.' '.oci_result($usrqr, 1).'</a></b>'.$stradmin.'</td>';}
                 echo ' <td>'.oci_result($usrqr, 2).'</td>
                  <td>'.oci_result($usrqr, 3).'</td>';
      echo '<td><div class="progress progress-striped progress-success" style="margin-bottom:0px;"><div style="width: '.$comprador.'%;" class="bar"> '.$comprador.'</div></div></td>';
      echo '<td><div class="progress progress-striped progress-warning" style="margin-bottom:0px;"><div style="width: '.$vendedor.'%;" class="bar"> '.$vendedor.'</div></div></td>';
      echo '<td><center><a href="profile.php?user='.oci_result($usrqr,1).'"><button class="btn btn-primary btn-mini"><i class="icon-eye-open icon-white"></i></button></a>
            <a href="edituser.php?user='.oci_result($usrqr,1).'"><button class="btn btn-inverse btn-mini"><i class="icon-pencil icon-white"></i></button></a>';
            if(oci_result($usrqr, 7)==0){
            echo '<a href="profile.php?user='.oci_result($usrqr,1).'&del=1"> <button class="btn btn-danger btn-mini"><i class="icon-remove icon-white"></i></button></a></center></td>';}
            else {echo '<a href="profile.php?user='.oci_result($usrqr,1).'&del=0"> <button class="btn btn-success btn-mini"><i class="icon-ok icon-white"></i></button></a></center></td>';}
      echo '</tr>';
    }
    oci_free_statement($usrqr);
  }

  public function getuser($conn){
      $user=$_GET['user'];
      $usrqr=oci_parse($conn, "SELECT u.*, f_idade(datanascimento) FROM Utilizadores u where nomeutilizador='$user'");
      $admin=$this->contarows($conn, "SELECT count(*) from administradores where login='$user'");
      oci_execute($usrqr);
      if(oci_fetch($usrqr)) {
        $birthtime = oci_parse($conn, "SELECT EXTRACT(year FROM datanascimento) Ano, EXTRACT(month FROM datanascimento) MES, EXTRACT(day FROM datanascimento) DIA FROM utilizadores where nomeutilizador='$user'");
        $regtime = oci_parse($conn, "SELECT EXTRACT(year FROM dataregisto) Ano, EXTRACT(month FROM dataregisto) MES, EXTRACT(day FROM dataregisto) DIA FROM utilizadores where nomeutilizador='$user'");
        oci_execute($birthtime);
        oci_fetch($birthtime);
        oci_execute($regtime);
        oci_fetch($regtime);
        $strbirth=oci_result($birthtime, 3).'-'.oci_result($birthtime, 2).'-'.oci_result($birthtime, 1);
        $strreg=oci_result($regtime, 3).'-'.oci_result($regtime, 2).'-'.oci_result($regtime, 1);
        $timestrbirth=date('d-m-Y',strtotime($strbirth));
        $timestrreg=date('d-m-Y',strtotime($strreg));
        $res= array('username' => oci_result($usrqr,1) , 
                    'nome' => oci_result($usrqr, 2), 
                    'email' => oci_result($usrqr, 3),
                    'morada' => oci_result($usrqr, 5), 
                    'telemovel' => oci_result($usrqr, 6),
                    'localidade' => oci_result($usrqr, 7), 
                    'codigopostal' => oci_result($usrqr, 8),
                    'datanascimento' => $timestrbirth, 
                    'dataregisto' => $timestrreg,
                    'avalcomp' => oci_result($usrqr, 13), 
                    'avalvend' => oci_result($usrqr, 24),
                    'imagem' => oci_result($usrqr, 14), 
                    'descricao' => oci_result($usrqr, 15),
                    'nrvendas' => oci_result($usrqr, 17), 
                    'nrcompras' => oci_result($usrqr, 18),
                    'totalganho' => oci_result($usrqr, 19), 
                    'totalgasto' => oci_result($usrqr, 20),
                    'totalavender' => oci_result($usrqr, 21), 
                    'avalpos' => oci_result($usrqr, 22),
                    'avalneg' => oci_result($usrqr, 23), 
                    'avalposv' => oci_result($usrqr, 24),
                    'avalnegv' => oci_result($usrqr, 25),
                    'apagado' => oci_result($usrqr, 28),
                    'paypal' => oci_result($usrqr, 10),
                    'mbnet' => oci_result($usrqr, 11),
                    'idade'=>oci_result($usrqr, 29),
                    'admin' => $admin);
          oci_free_statement($usrqr);
          return $res;
      } else {oci_free_statement($usrqr);header("location:users.php");}
  }

  public function banunban($conn){
    $user=$_GET['user'];
    if (isset($_GET['del'])){
      if($_GET['del']==1){
        $sqlban = "UPDATE utilizadores set apagado = 1 where nomeutilizador='$user' ";
        $banuser=oci_parse($conn, $sqlban);
        oci_execute($banuser);
        oci_free_statement($banuser);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O utilizador '.$user.' foi banido com sucesso.
            </div>';
      }
      elseif ($_GET['del']==0) {
        $sqlunban = "UPDATE utilizadores set apagado = 0 where nomeutilizador='$user' ";
        $unbanuser=oci_parse($conn, $sqlunban);
        oci_execute($unbanuser);
        oci_free_statement($unbanuser);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O utilizador '.$user.' foi desbanido com sucesso.
            </div>';
      }
    }
  }  



  public function makeadmin($conn, $admin){
    if (isset($_GET['admin']) and ($_GET['admin'])==1 and $admin==0){
      $user=$_GET['user'];
      $pass=oci_parse($conn, "SELECT password from utilizadores where nomeutilizador='$user'");
      oci_execute($pass);
      oci_fetch($pass);
      $pw=oci_result($pass, 1);
      oci_free_statement($pass);
        $sqladmin = oci_parse($conn,"INSERT into administradores (login, password) values ('$user', md5('$pw'))");
        oci_execute($sqladmin);
        oci_free_statement($sqladmin);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O utilizador '.$user.' tornou-se administrador com sucesso.
            </div>';
      }
    elseif (isset($_GET['admin']) and ($_GET['admin'])==0 ){
      $user=$_GET['user'];
      $pass=oci_parse($conn, "DELETE from administradores where login='$user'");
      oci_execute($pass);
      oci_free_statement($pass);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O utilizador '.$user.' deixou as suas funções como administrador.
            </div>';
      }
    }

  public function localidades($conn){
    $localidades=oci_parse($conn, "SELECT * from localidades");
    oci_execute($localidades);
    while (oci_fetch($localidades)) {
      echo '<option>'.oci_result($localidades, 1).'</option><br/>';
    }
    oci_free_statement($localidades);
  }





  public function edituser($conn){
    if(isset($_POST['submit'])){
      if($_POST['nome']!=null and $_POST['email']!=null and $_POST['contacto']!=null and $_POST['morada']!=null  and $_POST['codpostal']!=null and $_POST['localidade']!=null){
        $user=$_GET['user'];
        $nome=$_POST['nome'];
        $dataregisto=$_POST['dataregisto'];
        $email=$_POST['email'];
        $contacto=$_POST['contacto'];
        $datanascimento=$_POST['datanascimento'];
        $morada=$_POST['morada'];
        $codpostal=$_POST['codpostal'];
        $localidade=$_POST['localidade'];
        $descricao=$_POST['descricao'];
        $mbnet=$_POST['mbnet'];
        $paypal=$_POST['paypal'];
        $erro=0;
        $checkmail=$this->contarows($conn,"SELECT * from utilizadores where email='$email' and nomeutilizador!='$user'");
        $checktlmv=$this->contarows($conn,"SELECT * from utilizadores where telemovel='$contacto' and nomeutilizador!='$user'");
        if(isset($_POST['password']) && isset($_POST['checkpassword']) ){
          $pw=$_POST['password'];
          $checkpw=$_POST['checkpassword'];
          if($pw==$checkpw && $pw!="" && $checkpw!=""){
            $update=oci_parse($conn, "UPDATE utilizadores set password=md5('$pw') where nomeutilizador='$user'");
              oci_execute($update);
              oci_free_statement($update);
              echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> A password foi alterada com sucesso!
              </div>';
          }
          elseif( $pw!=$checkpw){
            $erro=1;
            echo '<div class="alert alert-error">
                  <button class="close" data-dismiss="alert">×</button>
                  <strong>Erro!</strong> As passwords inseridas não coincidem!
                 </div>';
          }
        }
        if($checktlmv==0 && $checkmail==0 && $erro==0){
          $update=oci_parse($conn, "UPDATE utilizadores set nome='$nome', email='$email', morada='$morada', telemovel='$contacto', localidade='$localidade',
                      codigopostal='$codpostal', datanascimento=to_timestamp('$datanascimento', 'dd-mm-yyyy'), dataregisto=to_timestamp('$dataregisto', 'dd-mm-yyyy'),
                      descricao='$descricao', contapaypal='$paypal', contambnet='$mbnet' where nomeutilizador='$user'");
          oci_execute($update);
          oci_free_statement($update);
          echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> As alterações foram guardadas com sucesso!
              </div>';
        }
        elseif ($checktlmv>0){
          echo '<div class="alert alert-error">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Erro!</strong> O contacto que tentou inserir já se encontra registado noutra conta!
              </div>';
        }
        elseif ($checkmail>0) {
          echo '<div class="alert alert-error">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Erro!</strong> O email que tentou inserir já se encontra registado noutra conta!
              </div>';
        }
    }
    elseif($erro=0) {
      echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> Deixou campos por preencher, por favor preencha os campos e tente guardar novamente.
             </div>';
    }
    unset($_POST);
    }
  }


  public function getbuycats($conn){
    $user=$_GET['user'];
    $sql=oci_parse($conn, "SELECT cat.nome, count(res.nome) as contagem from 
      (SELECT c.nome from categorias c, produtos p, historico h where h.comprador='$user' and c.nome=p.categoria and p.id=h.idproduto) res, categorias cat 
        where cat.nome=res.nome group by cat.nome");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;
  }

    public function getsellcats($conn){
    $user=$_GET['user'];
    $sql=oci_parse($conn, "SELECT cat.nome, count(res.nome) as contagem from 
      (SELECT c.nome from categorias c, produtos p, historico h where p.nomeutilizador='$user' and c.nome=p.categoria and p.id=h.idproduto) res, categorias cat 
        where cat.nome=res.nome group by cat.nome");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;
  }


  public function getforsale($conn){
    $user=$_GET['user'];
    $sql=oci_parse($conn, "SELECT p.*,TO_CHAR(p.datacolocacao,'DD-MM-YYYY') from produtos p where nomeutilizador='$user' and datavenda is null");
    oci_execute($sql);
    while(oci_fetch($sql)){
      if(oci_result($sql, 9)=="Leilão"){$str='<span class="label label-info">'.oci_result($sql, 9).'</span>';}
      else {$str='<span class="label label-success">'.oci_result($sql, 9).'</span>';}
      $image=oci_parse($conn, "SELECT foto from fotos where idproduto=".oci_result($sql, 1));
      oci_execute($image);
      oci_fetch($image);
      echo             
          '<div class="span11" style="margin-left:0px;"><div class="span2">
            <ul class="thumbnails" >
              <li class="span2" style="height:120px;width:120px;">
                <a class="thumbnail">
                  ';
      if( oci_result($image, 1) != null){
        $img = oci_result($image, 1)->load();
        print('<img style="max-height:110px;max-width:110px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
      }
      else {
        print('<img style="max-height:110px;max-width:110px;"  src="img/item.png" />'); 
      }

      echo '
                </a>
              </li>
            </ul>
          </div>
                          <div class="span8"><div class="span5"><p><i class="icon-tag"></i><b style="margin-right:30px;"><a href="item.php?id='.oci_result($sql, 1).'"> '.oci_result($sql, 2).'</a></div> </b><div class="span7"> ><a href="products.php?cat='.oci_result($sql, 11).'"> '.oci_result($sql, 11).'</a> > <a href="products.php?cat='.oci_result($sql, 11).'&subcat='.oci_result($sql, 12).'">'.oci_result($sql, 12).'</a></p></div>
                          <div class="span8"><p>'.oci_result($sql, 4).'</p></div>
                            <div class="span5">
                              <p><i class="icon-info-sign"></i> Estado: '.oci_result($sql, 5).'</p>
                              <p><i class="icon-th"></i> Quantidade: '.oci_result($sql, 6).'</p>
                              
                            </div>


                            <div class="span5">
                              <p><i class="icon-time"></i> Colocado em: '.oci_result($sql, 17).'</p>
                              <p><i class="icon-map-marker"></i> Localidade: '.oci_result($sql, 10).'</p>
                              
                            </div>
                            
                            
                            </div>
                          <div class="span2"><p><b>Preço:</b> '.oci_result($sql, 3).' €</p>
                            <p>'.$str.'</p></div>
                          <div class="span11" style="margin-top:-20px;"><hr><br/></div></div>';
    }
  }



  public function getbought($conn){
    $user=$_GET['user'];
    $sql=oci_parse($conn, "SELECT p.*,h.*,TO_CHAR(h.data,'DD-MM-YYYY') from produtos p, historico h where p.id=h.idproduto and h.comprador='$user'");
    oci_execute($sql);
    while(oci_fetch($sql)){
      if(oci_result($sql, 9)=="Leilão"){$str='<span class="label label-info">'.oci_result($sql, 9).'</span>';}
      else {$str='<span class="label label-success">'.oci_result($sql, 9).'</span>';}
      $image=oci_parse($conn, "SELECT foto from fotos where idproduto=".oci_result($sql, 1));
      oci_execute($image);
      oci_fetch($image);
      echo             
          '<div class="span11" style="margin-left:0px;"><div class="span2">
            <ul class="thumbnails" >
              <li class="span2" style="height:120px;width:120px;">
                <a class="thumbnail">
                  ';
      if( oci_result($image, 1) != null){
        $img = oci_result($image, 1)->load();
        print('<img style="max-height:110px;max-width:110px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
      }
      else {
        print('<img style="max-height:110px;max-width:110px;"  src="img/item.png" />'); 
      }

      echo '
                </a>
              </li>
            </ul>
          </div>
                          <div class="span8"><div class="span5"><p><i class="icon-tag"></i><b style="margin-right:30px;"><a href="item.php?id='.oci_result($sql, 1).'"> '.oci_result($sql, 2).'</a></div> </b><div class="span7"> ><a href="products.php?cat='.oci_result($sql, 11).'"> '.oci_result($sql, 11).'</a> > <a href="products.php?cat='.oci_result($sql, 11).'&subcat='.oci_result($sql, 12).'">'.oci_result($sql, 12).'</a></p></div>
                          <div class="span8"><p>'.oci_result($sql, 4).'</p></div>
                            <div class="span5">
                              <p><i class="icon-info-sign"></i> Estado: '.oci_result($sql, 5).'</p>
                              <p><i class="icon-map-marker"></i> Localidade: '.oci_result($sql, 10).'</p>
                              
                            </div>


                            <div class="span5">
                              <p><i class="icon-time"></i> Data de compra: '.oci_result($sql, 22).'</p>
                              <p><i class="icon-user"></i> Vendedor: <a href="profile.php?user='.oci_result($sql, 13).'">'.oci_result($sql, 13).'</a></p>
                              
                            </div>
                            
                            
                            </div>
                          <div class="span2"><p><b>Preço:</b> '.oci_result($sql, 3).' €</p>
                            <p>'.$str.'</p></div>
                          <div class="span11" style="margin-top:-20px;"><hr><br/></div></div>';
    }
  }  

  public function getsold($conn){
    $user=$_GET['user'];
    $sql=oci_parse($conn, "SELECT p.*,h.*,TO_CHAR(h.data,'DD-MM-YYYY') from produtos p, historico h where p.id=h.idproduto and p.nomeutilizador='$user'");
    oci_execute($sql);
    while(oci_fetch($sql)){
      if(oci_result($sql, 9)=="Leilão"){$str='<span class="label label-info">'.oci_result($sql, 9).'</span>';}
      else {$str='<span class="label label-success">'.oci_result($sql, 9).'</span>';}
      $image=oci_parse($conn, "SELECT foto from fotos where idproduto=".oci_result($sql, 1));
      oci_execute($image);
      oci_fetch($image);
      echo             
          '<div class="span11" style="margin-left:0px;"><div class="span2">
            <ul class="thumbnails" >
              <li class="span2" style="height:120px;width:120px;">
                <a class="thumbnail">
                  ';
      if( oci_result($image, 1) != null){
        $img = oci_result($image, 1)->load();
        print('<img style="max-height:110px;max-width:110px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
      }
      else {
        print('<img style="max-height:110px;max-width:110px;"  src="img/item.png" />'); 
      }

      echo '
                </a>
              </li>
            </ul>
          </div>
                          <div class="span8"><div class="span5"><p><i class="icon-tag"></i><b style="margin-right:30px;"><a href="item.php?id='.oci_result($sql, 1).'"> '.oci_result($sql, 2).'</a></div> </b><div class="span7"> ><a href="products.php?cat='.oci_result($sql, 11).'"> '.oci_result($sql, 11).'</a> > <a href="products.php?cat='.oci_result($sql, 11).'&subcat='.oci_result($sql, 12).'">'.oci_result($sql, 12).'</a></p></div>
                          <div class="span8"><p>'.oci_result($sql, 4).'</p></div>
                            <div class="span5">
                              <p><i class="icon-info-sign"></i> Estado: '.oci_result($sql, 5).'</p>
                              <p><i class="icon-map-marker"></i> Localidade: '.oci_result($sql, 10).'</p>
                              
                            </div>


                            <div class="span5">
                              <p><i class="icon-time"></i> Data de venda: '.oci_result($sql, 22).'</p>
                              <p><i class="icon-user"></i> Comprador: <a href="profile.php?user='.oci_result($sql, 18).'">'.oci_result($sql, 18).'</a></p>
                              
                            </div>
                            
                            
                            </div>
                          <div class="span2"><p><b>Preço:</b> '.oci_result($sql, 3).' €</p>
                            <p>'.$str.'</p></div>
                          <div class="span11" style="margin-top:-20px;"><hr><br/></div></div>';
    }
  }    

// Funções de Categorias

  public function newcat($conn){
    if(isset($_POST['newcat']) && ($_POST['newcat']!=null)){
      $new=$_POST['newcat'];
      $catqr=oci_parse($conn, "SELECT * from categorias where nome='$new'");
      oci_execute($catqr);
      $res1=array();
      oci_fetch_all($catqr,$res1);
      $nr=oci_num_rows($catqr);
      oci_free_statement($catqr);
      if($nr==0){
        $newcatqr=oci_parse($conn, "INSERT INTO categorias (nome) values ('$new')");
        oci_execute($newcatqr);
        oci_free_statement($newcatqr);
        echo '<div class="alert alert-success">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Sucesso!</strong> A categoria '.$new.' foi inserida com sucesso. Para adicionar subcategorias clique <a href="subcategories.php?cat='.$new.'"> aqui</a> .
            </div>';
      }
      else {echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> A categoria que tentou inserir já existe. Verifique na tabela abaixo se a categoria que iria criar corresponde aos seus critérios.
            </div>';}
    }
    if(isset($_POST['submit']) && ($_POST['newcat']==null)){echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> Tentou introduzir uma nova categoria mas não lhe atribui nenhum nome!
            </div>';}
    unset($_POST);
  }

  public function newsubcat($conn){
    $cat=$_GET['cat'];
    if(isset($_POST['newsubcat']) && ($_POST['newsubcat']!=null)){
      $new=$_POST['newsubcat'];
      $catqr=oci_parse($conn, "SELECT * from subcategorias where nome='$new' and categoria='$cat'");
      oci_execute($catqr);
      $res1=array();
      oci_fetch_all($catqr,$res1);
      $nr=oci_num_rows($catqr);
      oci_free_statement($catqr);
      if($nr==0){
        $newsubcatqr=oci_parse($conn, "INSERT INTO subcategorias (nome,categoria) values ('$new','$cat')");
        oci_execute($newsubcatqr);
        oci_free_statement($newsubcatqr);
        echo '<div class="alert alert-success">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Sucesso!</strong> A subcategoria '.$new.' foi inserida com sucesso.
            </div>';
      }
      else {echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Error!</strong> A subcategoria que tentou inserir já existe. Verifique na tabela abaixo se a subcategoria que iria criar corresponde aos seus critérios.
            </div>';}
    }
    if(isset($_POST['submit']) && ($_POST['newsubcat']==null)){echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Error!</strong> Tentou introduzir uma nova subcategoria mas não lhe atribui nenhum nome!
            </div>';}
    unset($_POST);
  }

   public function getcats($conn){
      $catstotalqrstr="SELECT id from produtos";
      $catstotalqr=oci_parse($conn, $catstotalqrstr);
      oci_execute($catstotalqr);
      $res1=array();
      oci_fetch_all($catstotalqr,$res1);
      $nrtotal=oci_num_rows($catstotalqr);
      oci_free_statement($catstotalqr);
      $catqr=oci_parse($conn, "SELECT nome FROM categorias ORDER BY nome ASC");
      oci_execute($catqr);
      while(oci_fetch($catqr)){
        $aux1=oci_result($catqr, 1);
        $cattotalqr=oci_parse($conn, "SELECT id from produtos where categoria='$aux1'");
        oci_execute($cattotalqr);
        oci_fetch_all($cattotalqr,$res2);
        $nrprod=oci_num_rows($cattotalqr);
        oci_free_statement($cattotalqr);
        $subcatqr=oci_parse($conn, "SELECT nome from subcategorias where categoria='$aux1'");
        oci_execute($subcatqr);
        $res3=array();
        oci_fetch_all($subcatqr,$res3);
        $nrsub=oci_num_rows($subcatqr);
        oci_free_statement($subcatqr);
        if($nrtotal>0){$perc=round(($nrprod/$nrtotal)*100,2);} else {$perc=0;}
        echo '<tr>
                    <td><a href="subcategories.php?cat='.$aux1.'">'.oci_result($catqr, 1).'</a></td>
                    <td><center>'.$nrsub.'</center></td>
                    <td><center>'.$nrprod.'</center></td>
                    <td><div class="progress progress-striped progress-success" style="margin-bottom:0px;"><div style="width: '.$perc.'%;" class="bar"> '.$perc.'%</div></div></td>';
        echo '<td><center><a href="subcategories.php?cat='.$aux1.'"><button class="btn btn-primary btn-mini"><i class=" icon-eye-open icon-white"></i></button></a> 
              <a href="subcategories.php?cat='.oci_result($catqr, 1).'&del=1"><button class="btn btn-danger btn-mini"><i class="icon-remove icon-white"></i></button></a></center></td>
                    </tr>';
      }
    oci_free_statement($catqr);
  } 

    public function getsubcats($conn){
      $cat=$_GET['cat'];
      $subcatprodstr="SELECT id from produtos where categoria='$cat'";
      $subcattotalqr=oci_parse($conn, $subcatprodstr);
      oci_execute($subcattotalqr);
      $res1=array();
      oci_fetch_all($subcattotalqr,$res1);
      $nrtotal=oci_num_rows($subcattotalqr);
      oci_free_statement($subcattotalqr);
      $subcatqr=oci_parse($conn, "SELECT nome FROM subcategorias where categoria='$cat'");
      oci_execute($subcatqr);
      while(oci_fetch($subcatqr)){
        $aux1=oci_result($subcatqr, 1);
        $subcattotalqr=oci_parse($conn, "SELECT id from produtos where subcategoria='$aux1' and categoria='$cat'");
        oci_execute($subcattotalqr);
        oci_fetch_all($subcattotalqr,$res2);
        $nrprod=oci_num_rows($subcattotalqr);
        oci_free_statement($subcattotalqr);
        if($nrtotal>0){$perc=round(($nrprod/$nrtotal)*100,2);} else {$perc=0;}
        echo '<tr>
                    <td>'.oci_result($subcatqr, 1).'</td>
                    <td>'.$nrprod.'</td>
                    <td><div class="progress progress-striped progress-success" style="margin-bottom:0px;"><div style="width: '.$perc.'%;" class="bar"> '.$perc.'%</div></div></td>';
        echo '<td><center><button class="btn btn-primary btn-mini"><i class="icon-user icon-white"></i></button> <button class="btn btn-inverse btn-mini"><i class="icon-pencil icon-white"></i></button> <button class="btn btn-danger btn-mini"><i class="icon-remove icon-white"></i></button></center></td>
                    </tr>';
      }
    oci_free_statement($subcatqr);
  }


  
// Produtos 
    public function getcategorias($conn){
    $categorias=oci_parse($conn, "SELECT * from subcategorias order by categoria");
    oci_execute($categorias);
    if(isset($_POST['categoria'])){ $cat=$_POST['categoria'] ;} else {$cat="x";}
    while (oci_fetch($categorias)) {
      $str= oci_result($categorias, 2).' - '.oci_result($categorias, 1);
      if(strcmp($str,$cat) == 0){$select="selected";} else {$select="";}
      echo '<option '.$select.' >'.$str.'</option><br/>';
    }
    oci_free_statement($categorias);
  }    

  public function getlocalidades($conn){
    $localidades=oci_parse($conn, "SELECT * from localidades");
    oci_execute($localidades);
    if(isset($_POST['localidade'])){ $local=$_POST['localidade'] ;} else {$local="x";}
    while (oci_fetch($localidades)) {
      $str=oci_result($localidades, 1);
      if(strcmp($str, $local)==0){$sel="selected";} else {$sel="";}
      echo '<option '.$sel.' >'.oci_result($localidades, 1).'</option><br/>';
    }
    oci_free_statement($localidades);
  }

  public function getitemlocalidades($conn,$item){
    $localidades=oci_parse($conn, "SELECT * from localidades");
    oci_execute($localidades);
    $local=$item['localidade'];
    while (oci_fetch($localidades)) {
      $str=oci_result($localidades, 1);
      if(strcmp($str, $local)==0){$sel="selected";} else {$sel="";}
      echo '<option '.$sel.' >'.oci_result($localidades, 1).'</option><br/>';
    }
    oci_free_statement($localidades);
  }



    public function getprodutos($conn){
      if(isset($_POST['procura']) && $_POST['procura']!=null){$procura = " and (upper(p.nome) like upper('%".$_POST['procura']."%') or upper(p.descricao) like upper('%".$_POST['procura']."%')) ";} else { $procura = ""; }
      if(isset($_POST['radios']) && $_POST['radios']!=null){if($_POST['radios']!="Vendido"){$venda = " and p.metodovenda='".$_POST['radios']."' ";} else { $venda=" and p.datavenda is not null " ;} } else { $venda = ""; }
      
      if(isset($_POST['categoria']) && $_POST['categoria']!=null)
        {
          $catstr = $_POST['categoria'];
          $catarray = explode(" - ", $catstr);
          $categoria = " and (categoria='".$catarray[0]."' and subcategoria='".$catarray[1]."') ";
        } 
      else { $categoria = ""; }
      
      if(isset($_POST['vendedor']) && $_POST['vendedor']!=null){$vendedor = " and p.nomeutilizador='".$_POST['vendedor']."' ";} else { $vendedor = ""; }
      if(isset($_POST['comprador']) && $_POST['comprador']!=null){$comprador =" and p.id=h.idproduto and h.comprador ='".$_POST['comprador']."' ";} else { $comprador = ""; }
      if(isset($_POST['minprice']) && $_POST['minprice']!=null){$minprice = " and p.preco>=".$_POST['minprice']." ";} else { $minprice = ""; }
      if(isset($_POST['maxprice']) && $_POST['maxprice']!=null){$maxprice =" and p.preco<=".$_POST['maxprice']." ";} else { $maxprice = ""; }
      if(isset($_POST['localidade']) && $_POST['localidade']!=null){$localidade =" and localidade='".$_POST['localidade']."' ";} else { $localidade = ""; }
      if(isset($_POST['metpay']) && $_POST['metpay']!=null){$metpay = "and p.id=m.idproduto and m.nomemetodo='".$_POST['metpay']."' ";} else { $metpay = ""; }





    $sql=oci_parse($conn, "SELECT distinct p.*, to_char(p.datacolocacao, 'DD-MM-YYYY') from produtos p where 1=1 ".$procura." ".$venda." ".$categoria." ".$vendedor." ".$comprador." ".$minprice." ".$maxprice." ".$localidade." ".$metpay."");
    oci_execute($sql);
    $i=0;
    while(oci_fetch($sql)){
      $i++;
      if(oci_result($sql, 9)=="Leilão"){$str='<span class="label label-info">'.oci_result($sql, 9).'</span>';}
      else {$str='<span class="label label-success">'.oci_result($sql, 9).'</span>';}
      $image=oci_parse($conn, "SELECT foto from fotos where idproduto=".oci_result($sql, 1));
      oci_execute($image);
      oci_fetch($image);
      echo             
          '<tr><td><br/><div class="span1"></div><div class="span10" style="margin-left:0px;"><div class="span2">
            <ul class="thumbnails" >
              <li class="span2" style="height:120px;width:120px;">
                <a class="thumbnail">
                  ';
      if( oci_result($image, 1) != null){
        $img = oci_result($image, 1)->load();
        print('<img style="max-height:110px;max-width:110px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
      }
      else {
        print('<img style="max-height:110px;max-width:110px;"  src="img/item.png" />'); 
      }

      echo '
                </a>
              </li>
            </ul>
          </div>
                          <div class="span8"><div class="span5"><p><i class="icon-tag"></i><b style="margin-right:30px;"><a href="item.php?id='.oci_result($sql, 1).'"> '.oci_result($sql, 2).'</a></div> </b><div class="span7"> ><a href="products.php?cat='.oci_result($sql, 11).'"> '.oci_result($sql, 11).'</a> > <a href="products.php?cat='.oci_result($sql, 11).'&subcat='.oci_result($sql, 12).'">'.oci_result($sql, 12).'</a></p></div>
                          <div class="span8"><p><blockquote>'.oci_result($sql, 4).'</blockquote></p></div>
                            <div class="span5">
                              <p><i class="icon-info-sign"></i> Estado: '.oci_result($sql, 5).'</p>
                              <p><i class="icon-th"></i> Quantidade: '.oci_result($sql, 6).'</p>
                              
                            </div>


                            <div class="span5">
                              <p><i class="icon-time"></i> Colocado em: '.oci_result($sql, 17).' </p>
                              <p><i class="icon-map-marker"></i> Localidade: '.oci_result($sql, 10).'</p>
                              
                            </div>
                            
                            
                            </div>
                          <div class="span2"><p><b>Preço:</b> '.oci_result($sql, 3).' €</p>
                            <p>'.$str.'</p></div>
                          </div><div class="span1"></div></td></tr>';
    } echo '<div style="margin-left:30px;"> > A procura devolveu '.$i.' produtos!';
  }


  public function getItem($conn){
      $produto=$_GET['id'];
      $produtoqr=oci_parse($conn, "SELECT p.*,f.foto,to_char(p.datacolocacao, 'DD-MM-YYYY')  from produtos p, fotos f where p.id=$produto and p.id=f.idproduto");
      oci_execute($produtoqr);
      if(oci_fetch($produtoqr)) {
        $res= array('id' => oci_result($produtoqr,1) , 
                    'nome' => oci_result($produtoqr, 2), 
                    'preco' => oci_result($produtoqr, 3),
                    'descricao' => oci_result($produtoqr, 4), 
                    'estado' => oci_result($produtoqr, 5),
                    'quantidade' => oci_result($produtoqr, 6), 
                    'imagem' => oci_result($produtoqr, 17),
                    'datacolocacao' => oci_result($produtoqr, 18),
                    'metodovenda' => oci_result($produtoqr, 9),
                    'localidade' => oci_result($produtoqr, 10), 
                    'categoria' => oci_result($produtoqr, 11),
                    'subcategoria' => oci_result($produtoqr, 12), 
                    'vendedor' => oci_result($produtoqr, 13),
                    'datavenda' => oci_result($produtoqr, 14), 
                    'apagado' => oci_result($produtoqr, 16)
                    );
          oci_free_statement($produtoqr);
          return $res;
      } else {oci_free_statement($produtoqr);header("location:products.php");}
  }

    public function deleteundelete($conn){
    $id=$_GET['id'];
    if (isset($_GET['del'])){
      if($_GET['del']==1){
        $sqldel = "UPDATE produtos set apagado = 1 where id=$id ";
        $delitem=oci_parse($conn, $sqldel);
        oci_execute($delitem);
        oci_free_statement($delitem);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O produto foi apagado com sucesso.
            </div>';
      }
      elseif ($_GET['del']==0) {
        $sqlundel = "UPDATE produtos set apagado = 0 where id=$id ";
        $undelitem=oci_parse($conn, $sqlundel);
        oci_execute($undelitem);
        oci_free_statement($undelitem);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> O produto foi desbloqueado com sucesso.
            </div>';
      }
    }
  }


    public function getItemcategorias($conn, $item){
    $categorias=oci_parse($conn, "SELECT * from subcategorias order by categoria");
    oci_execute($categorias);
    $cat=$item['categoria']." - ".$item['subcategoria'];
    while (oci_fetch($categorias)) {
      $str= oci_result($categorias, 2).' - '.oci_result($categorias, 1);
      if(strcmp($str,$cat) == 0){$select="selected";} else {$select="";}
      echo '<option '.$select.' >'.$str.'</option><br/>';
    }
    oci_free_statement($categorias);
  }    


  public function edititem($conn){
    if(isset($_POST['submit'])){
      if($_POST['nome']!=null && $_POST['vendedor']!=null && $_POST['datacolocacao']!=null && $_POST['estado']!=null  && $_POST['categoria']!=null && $_POST['metvenda']!=null && $_POST['preco']!=null && $_POST['quantidade']!=null && $_POST['descricao']!=null){
        $usercenas=$_POST['vendedor'];
        if($this->contarows($conn, "SELECT count(*) from utilizadores where nomeutilizador='$usercenas'")==1){
        $id=$_GET['id'];
        $vendedor=$_POST['vendedor'];
        $nome=$_POST['nome'];
        $datacol=$_POST['datacolocacao'];
        $estado=$_POST['estado'];
        $categoria=$_POST['categoria'];
        $metvenda=$_POST['metvenda'];
        $preco=$_POST['preco'];
        $quantidade=$_POST['quantidade'];
        $descricao=$_POST['descricao'];
        $catarray = explode(" - ", $categoria);
        $cmdsql=oci_parse($conn,"UPDATE produtos set nome='$nome', nomeutilizador='$vendedor', datacolocacao=to_timestamp('$datacol', 'dd-mm-yyyy'), estado='$estado',
                             categoria='".$catarray[0]."', subcategoria='".$catarray[1]."', metodovenda='$metvenda', preco=$preco, quantidade=$quantidade, descricao='$descricao'  where id=$id ");
        oci_execute($cmdsql);
        oci_free_statement($cmdsql); 
        echo '<div class="alert alert-success">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Sucesso!</strong> Produto alterado com sucesso!
             </div>';}
        else{ 
          echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> O nome de vendedor que introduziu não existe.
             </div>';
        }
      }                  
    else {
      echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> Deixou campos por preencher, por favor preencha os campos e tente guardar novamente.
             </div>';
    }
    unset($_POST);
    }
  }



/*Funcs das retricoes*/
 
    public function getFilters($conn){
      $restricoesstr="SELECT * from Restricoes";
      $restricoesqr=oci_parse($conn, $restricoesstr);
      oci_execute($restricoesqr);
      while(oci_fetch($restricoesqr)){
        echo ' <a href="contentfilter.php?del='.oci_result($restricoesqr, 1).'"><button class="btn btn-danger btn-mini" style="margin-bottom:10px;"> '.oci_result($restricoesqr, 1).' <i class="icon-white icon-remove"></i></button></a>  ';
 
       
      }
    }
 
    public function removeFilter($conn){
      if(isset($_GET['del'])){
        $palavra=$_GET['del'];
        $sqldel = "DELETE FROM Restricoes WHERE palavra='$palavra' ";
        $delres=oci_parse($conn, $sqldel);
        oci_execute($delres);
        oci_free_statement($delres);
        echo '<div class="alert alert-info">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Info!</strong> A restrição sobre a(s) palavra(s) "'.$palavra.'"" deixou de existir.
            </div>';
    }
  }
 
  public function newFilter($conn){
    if(isset($_POST['newFilter']) && ($_POST['newFilter']!=null)){
      $new=$_POST['newFilter'];
      $filterqr=oci_parse($conn, "SELECT * from Restricoes where palavra='$new'");
      oci_execute($filterqr);
      $res1=array();
      oci_fetch_all($filterqr,$res1);
      $nr=oci_num_rows($filterqr);
      oci_free_statement($filterqr);
      if($nr==0){
        $newfilterqr=oci_parse($conn, "INSERT INTO Restricoes (palavra) values ('$new')");
        oci_execute($newfilterqr);
        oci_free_statement($newfilterqr);
        echo '<div class="alert alert-success">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Sucesso!</strong> A(s) palavra(s) '.$new.'  servem agora de filtro para os produtos à venda.
            </div>';
      }
      else {echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> A restrição que tentou inserir já está activa. Verifique na tabela abaixo.
            </div>';}
    }
    if(isset($_POST['submit']) && ($_POST['newFilter']==null)){echo '<div class="alert alert-error">
              <button class="close" data-dismiss="alert">×</button>
              <strong>Erro!</strong> Tentou introduzir uma nova categoria mas não lhe atribui nenhum nome!
            </div>';}
    unset($_POST);
  }
 
  public function getArrayRestricoes($conn){
      $arr = array();
      $i = 0;
      $restricoesstr="SELECT * from Restricoes";
      $restricoesqr=oci_parse($conn, $restricoesstr);
      oci_execute($restricoesqr);
      while(oci_fetch($restricoesqr)){
        $arr[$i] = oci_result($restricoesqr, 1);        
        $i++;
      }
      return $arr;
  }
 
 
  public function getFiltered($conn){
    $arr = $this->getArrayRestricoes($conn);
    $count = count($arr);
    if($count > 0){
      $sqlstr = "SELECT p.*, TO_CHAR(p.datacolocacao,'DD-MM-YYYY') from produtos p where p.nome like '%".$arr[0]."%' \n";
      for($i=1; $i< $count; $i++){
        $sqlstr.="UNION\n";
        $sqlstr.="SELECT p.*, TO_CHAR(p.datacolocacao,'DD-MM-YYYY') from produtos p where p.nome like '%".$arr[$i]."%' \n";
      } 
      $sql=oci_parse($conn, $sqlstr);
      oci_execute($sql);
      while(oci_fetch($sql)){
        if(oci_result($sql, 9)=="Leilão"){$str='<span class="label label-info">'.oci_result($sql, 9).'</span>';}
        else {$str='<span class="label label-success">'.oci_result($sql, 9).'</span>';}
        $image=oci_parse($conn, "SELECT foto from fotos where idproduto=".oci_result($sql, 1));
        oci_execute($image);
        oci_fetch($image);
        echo            
            '<div class="span12" style="margin-left:0px;"><div class="span2">
              <ul class="thumbnails" >
                <li class="span2" style="height:120px;width:120px;">
                  <a class="thumbnail">
                    ';
        if( oci_result($image, 1) != null){
          $img = oci_result($image, 1)->load();
          print('<img style="max-height:110px;max-width:110px;"  src="data:image/png;base64,'.base64_encode($img).'" />');
        }
        else {
          print('<img style="max-height:110px;max-width:110px;"  src="img/item.png" />');
        }
 
        echo '
                  </a>
                </li>
              </ul>
            </div>
                            <div class="span8"><div class="span5"><p><i class="icon-tag"></i><b style="margin-right:30px;"><a href="item.php?id='.oci_result($sql, 1).'"> '.oci_result($sql, 2).'</a></div> </b><div class="span7"> ><a href="products.php?cat='.oci_result($sql, 11).'"> '.oci_result($sql, 11).'</a> > <a href="products.php?cat='.oci_result($sql, 11).'&subcat='.oci_result($sql, 12).'">'.oci_result($sql, 12).'</a></p></div>
                            <div class="span8"><p>'.oci_result($sql, 4).'</p></div>
                              <div class="span5">
                              <p><i class="icon-info-sign"></i> Estado: '.oci_result($sql, 5).'</p>
                              <p><i class="icon-th"></i> Quantidade: '.oci_result($sql, 6).'</p>
                             
                            </div>
 
 
                            <div class="span5">
                              <p><i class="icon-time"></i> Colocado em: '.oci_result($sql, 17).'</p>
                              <p><i class="icon-map-marker"></i> Localidade: '.oci_result($sql, 10).'</p>
                             
                            </div>
                           
                           
                            </div>
                          <div class="span2"><p><b>Preço:</b> '.oci_result($sql, 3).' €</p>
                            <p>'.$str.'</p></div>
                          <div class="span11" style="margin-top:-20px;"><hr><br/></div></div>';
      }
    }
  }

/*   Acabam aqui as funcs das restricoes  */  

    public function getNumUtilizadores($conn){
    $sql1=oci_parse($conn, "SELECT count(*) as totalUtilizadores from Utilizadores u where to_char(u.dataregisto,'MM-YYYY') = to_char(CURRENT_TIMESTAMP, 'MM-YYYY')");
    $sql2=oci_parse($conn, "SELECT count(*) as totalUtilizadores from Utilizadores");
    $res= array();
    oci_execute($sql1);
    oci_fetch($sql1);
    $res[0]= oci_result($sql1, 1);
    oci_execute($sql2);
    oci_fetch($sql2);
    $res[1]= oci_result($sql2, 1);
    $res[2]= ($res[0] * 100)/ $res[1];
    return $res;
  }
  


  public function getNumProdutos($conn){
    $sql1=oci_parse($conn, "SELECT count(*) as totalProdutos from Produtos p where to_char(p.datacolocacao,'MM-YYYY') = to_char(CURRENT_TIMESTAMP, 'MM-YYYY')");
    $sql2=oci_parse($conn, "SELECT count(*) as totalProdutos from Produtos");
    $res= array();
    oci_execute($sql1);
    oci_fetch($sql1);
    $res[0]= oci_result($sql1, 1);
    oci_execute($sql2);
    oci_fetch($sql2);
    $res[1]= oci_result($sql2, 1);
    $res[2]= ($res[0] * 100)/ $res[1];
    return $res;  
  }

  public function getNumMensagens($conn){
    $sql1=oci_parse($conn, "SELECT count(*) as totalMensagens from Mensagens m where to_char(m.data,'MM-YYYY') = to_char(CURRENT_TIMESTAMP, 'MM-YYYY')");
    $sql2=oci_parse($conn, "SELECT count(*) as totalMensagens from Mensagens");
    $res= array();
    oci_execute($sql1);
    oci_fetch($sql1);
    $res[0]= oci_result($sql1, 1);
    oci_execute($sql2);
    oci_fetch($sql2);
    $res[1]= oci_result($sql2, 1);
    $res[2]= ($res[0] * 100)/ $res[1];
    return $res;  
  }

  public function getTotalDinheiro($conn){
    $sql1=oci_parse($conn, "SELECT sum(p.preco) as totalDinheiro from Produtos p where p.datavenda is not null and  to_char(p.datavenda, 'MM-YYYY') = to_char( CURRENT_TIMESTAMP, 'MM-YYYY')");
    $sql2=oci_parse($conn, "SELECT sum(p.preco) as totalDinheiro from Produtos p where p.datavenda is not null");
    $res= array();
    oci_execute($sql1);
    oci_fetch($sql1);
    $res[0]= oci_result($sql1, 1);
    oci_execute($sql2);
    oci_fetch($sql2);
    $res[1]= oci_result($sql2, 1);
    $res[2]= ($res[0] * 100)/ $res[1];
    return $res;
  }
  public function getLastRegistos($conn){
    $sql=oci_parse($conn, "SELECT count(*) as totalUtilizadores from Utilizadores");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res= oci_result($sql, 1);
    }
    echo $res;
  }

    public function getAdmin($conn){
    $sql=oci_parse($conn, "SELECT count(*) from Administradores");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res= oci_result($sql, 1);
    }
    echo $res;
  }

  public function getTopSellers($conn){
    $sql=oci_parse($conn, "SELECT nome,nomeutilizador, avaliacaovendedor FROM utilizadores WHERE ROWNUM < 8  order by avaliacaovendedor DESC");
    oci_execute($sql);
    $i=1;
    while(oci_fetch($sql)){
      echo '<tr>
              <td><b>'.$i.'º</b> <a href="profile.php?user='.oci_result($sql, 2).'"><b>'.oci_result($sql, 2).'</b></a></td>
              <td><div class="progress progress-striped progress-success" style="margin-bottom:0px;max-width:100%;"><div style="width: '.round(oci_result($sql, 3),2).'%;" class="bar">'.round(oci_result($sql, 3),2).'%</div></div></td>
            </tr>';
            $i++;
    }
  }

  public function getTopBuyers($conn){
    $sql=oci_parse($conn, "SELECT nomeutilizador, avaliacaocomprador from utilizadores WHERE ROWNUM < 8 order by avaliacaocomprador DESC");
    oci_execute($sql);
    $i=1;
    while(oci_fetch($sql)){
      echo '<tr>
              <td><b>'.$i.'º</b> <a href="profile.php?user='.oci_result($sql, 1).'"><b>'.oci_result($sql, 1).'</b></a></td>
              <td><div class="progress progress-striped progress-info" style="margin-bottom:0px;width:100%;"><div style="width: '.round(oci_result($sql, 2),2).'%;" class="bar">'.round(oci_result($sql, 2),2).'%</div></div></td>
            </tr>';
            $i++;
    }
  }

  public function getTopWishlist($conn){
    $sql=oci_parse($conn, "SELECT * from (Select p.nome, l.num,p.id from produtos p, (Select idProduto, count(*) as num from produtosdesejados
    group by idProduto) l where p.id = l.idProduto order by l.num DESC) where rownum < 8");
    $res="";
    oci_execute($sql);
    $i=1;
    while(oci_fetch($sql)){
      echo '<tr>
              <td><b>'.$i.'º</b> <a href="item.php?id='.oci_result($sql, 3).'"><b>'.oci_result($sql, 1).'</b></a></td>
              <td><center>'.round(oci_result($sql, 2),2).'</center></td>
            </tr>';
            $i++;
    }
  }

  public function getProdutosTipoVenda($conn){
    $sql=oci_parse($conn, "SELECT metodovenda, count(*) from produtos group by metodovenda");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;
  }

  public function getNumProdutosCategoria($conn){
    $sql=oci_parse($conn, "SELECT * from (Select categoria, count(*) from produtos group by categoria order by count(*) DESC) where rownum < 11");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    return $res;
  }  



  public function getNumProdutosLocalidade($conn){
    $sql=oci_parse($conn, "SELECT localidade, count(*) from produtos group by localidade");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    return $res;
  }

  public function getUserRegistadosData($conn){
    $sql=oci_parse($conn, "SELECT to_char(dataregisto,'YYYY-MM') as data, count(*) from utilizadores group by to_char(dataregisto,'YYYY-MM') order by data DESC");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    return $res;
  }

  public function getProdutosVendidosData($conn){
    $sql=oci_parse($conn, "SELECT to_char(datavenda,'MM-YYYY') as data, count(*) from produtos where datavenda is not null group by to_char(datavenda,'MM-YYYY')");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;
  }

  public function getProdutosColocadosData($conn){
    $sql=oci_parse($conn, "SELECT to_char(datacolocacao,'MM-YYYY') as data, count(*) from produtos where datavenda is not null group by to_char(datacolocacao,'MM-YYYY')");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;
  }

  public function getDinheiroGastoData($conn){
    $sql=oci_parse($conn, "Select to_char(datavenda,'MM-YYYY') as data, sum(preco) from produtos where datavenda is not null group by to_char(datavenda,'MM-YYYY')");
    $res="";
    oci_execute($sql);
    while(oci_fetch($sql)){
      $res=$res."['".oci_result($sql, 1)."', ".oci_result($sql, 2)."], ";
    }
    echo $res;    
  }


}

?>
