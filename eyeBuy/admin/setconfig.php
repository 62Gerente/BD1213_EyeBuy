		<?php
		    include_once('includes/functions.php');
		    require_once('config.php');
        	$admpanel = new adminpanel(DBHOST,DBUSER,DBPASS,DBCHRSET);
		    $conn= $admpanel->connect();
		    $admpanel->authcheck();
		    $newusers=$admpanel->getNumUtilizadores($conn);
		    $anuncios=$admpanel->getNumProdutos($conn);
		    $mensagens=$admpanel->getNumMensagens($conn);
		    $transaccoes=$admpanel->getTotalDinheiro($conn);
		?>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>eyeBuy Admin Panel</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/font-awesome.css" />
		<link rel="stylesheet" href="css/fullcalendar.css" />	
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />

	</head>
	<body>
		<div id="header">
				<h1><a href="./dashboard.php">eyeBuy Admin Panel</a></h1>ões</a>		
		</div>		
		<div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
            	<li class="btn btn-inverse" ><a><i class=" icon-thumbs-up"></i> <span class="text">Bem-vindo <?php echo $_SESSION['user'].'!'; ?></span></a></li>
                <li class="btn btn-inverse" ><?php echo '<a  href="profile.php?user='.$_SESSION['user'].'">';?><i class="icon icon-user"></i> <span class="text">Perfil</span></a></li>
                <li class="btn btn-inverse dropdown" id="menu-messages"><a data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Mensagens</span> <span class="label label-important"><?php echo $admpanel->msgnr($conn); ?></span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a class="sAdd" title="" href="newmessage.php">nova</a></li>
                        <li><a class="sInbox" title="" href="inbox.php">recebidas (<?php echo $admpanel->msgnr($conn); ?>)</a></li>
                        <li><a class="sOutbox" title="" href="outbox.php">enviadas</a></li>
                    </ul>
                </li>
                <li class="btn btn-inverse"><a title="" href="login.php"><i class="icon icon-share-alt"></i><span class="text">Logout</span></a></li>
            </ul>


        </div>

		<div id="sidebar">
			<ul>
				<li><a href="dashboard.php"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
				<li><a href="users.php"><i class="icon icon-user"></i> <span>Utilizadores</span></a></li>
				<li><a href="categories.php"><i class="icon  icon-th-list"></i> <span>Categorias</span></a></li>
				<li><a href="products.php"><i class="icon icon-gift"></i> <span>Produtos</span></a></li>
				<li><a href="tickets.php"><i class="icon icon-warning-sign"></i> <span>Notificações</span> <span class="label" style="background-color: #800;"><?php echo $admpanel->ticketnr($conn); ?></span><span class="label" style="background-color: #f89406;"><?php echo $admpanel->ticketnotsolved($conn); ?></span></a></li>
				<li><a href="contentfilter.php"><i class="icon icon-ban-circle"></i> <span>Filtro de conteúdos</span></a></li>
				<li class="active"><a href="setconfig.php"><i class="icon icon-cog"></i> <span>Configurações</span></a></li>
			</ul>
		</div>
		
		
		<div id="content">
			<div id="content-header">
				<h1>Configurações</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.pgp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
				<a href="setconfig.php" class="current">Configurações</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<?php
						$save=0;
						error_reporting(0);
						if(isset($_POST['host']) and isset($_POST['cod']) and isset($_POST['user']) and isset($_POST['password'])){
							$newconn = oci_connect($_POST['user'],$_POST['password'],$_POST['host'],$_POST['cod']);
							if($newconn){echo '<div class="alert alert-info">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Info!</strong> Conexão ao servidor estabelecida com sucesso, para tornar permanente clique em Guardar! 
               </div>';$save=1;}
							else {echo '<div class="alert alert-danger">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Erro!</strong> Não foi possível estabelecer ligação ao servidor!
               </div>';$save=0;}

						}
						if(isset($_POST['save']) and $save==1){
							$myFile = "config.php";
							$fh = fopen($myFile, 'w') or die("Falhou");
							$stringData = "<?php
											define('DBHOST', '".$_POST['host']."');
											define('DBUSER', '".$_POST['user']."');
											define('DBPASS', '".$_POST['password']."');
											define('DBCHRSET', '".$_POST['cod']."');
											?>";
							fwrite($fh, $stringData);
							fclose($fh);
							echo $stringData;
							echo '<div class="alert alert-success">
                <button class="close" data-dismiss="alert">×</button>
                <strong>Sucesso!</strong> Informações de conexão guardadas com sucesso!
               </div>';

						}

						?>
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>Configurações do Servidor</h5>
							</div>
							<div class="widget-content nopadding">
								<form  method="post" class="form-horizontal">
									<div class="control-group">
										<label class="control-label">Host/DB</label>
										<div class="controls">
											<input name="host" value="<?php if(isset($_POST['host'])){ echo $_POST['host'] ;} else{echo DBHOST;} ?>" type="text" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Codificação</label>
										<div class="controls">
											<input name="cod" value="<?php if(isset($_POST['cod'])){ echo $_POST['cod'] ;} else{echo DBCHRSET;} ?>" type="text" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Utilizador</label>
										<div class="controls">
											<input name="user" value="<?php if(isset($_POST['user'])){ echo $_POST['user'] ;} else{echo DBUSER;} ?>" type="text" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Password</label>
										<div class="controls">
											<input name="password" value="<?php if(isset($_POST['password'])){ echo $_POST['password'] ;} else{echo DBPASS;} ?>" type="text" />
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-primary"><i class=" icon-warning-sign"></i> Testar ligação</button>
									<?php if($save==1){ echo'
										<button type="submit" name="save" class="btn btn-success"><i class=" icon-ok"></i> Guardar ligação</button>
									';} ?>
									</div>
									
								</form>
							</div>
						</div>						
					</div>
				</div>
			</div>
		</div>

            <script src="js/excanvas.min.js"></script>
            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.flot.min.js"></script>
            <script src="js/jquery.flot.resize.min.js"></script>
            <script src="js/jquery.peity.min.js"></script>
            <script src="js/fullcalendar.min.js"></script>
            <script src="js/unicorn.js"></script>
	</body>
</html>
