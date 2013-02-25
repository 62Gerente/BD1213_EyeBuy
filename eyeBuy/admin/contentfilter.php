<?php
   
      include_once('includes/functions.php');
      require_once('config.php');
      $admpanel = new adminpanel(DBHOST,DBUSER,DBPASS,DBCHRSET);
 
      $conn= $admpanel->connect();
      $admpanel->authcheck();
   
    ?>
 
<!DOCTYPE html>
<html lang="en">
        <head>
                <title>eyeBuy Admin Panel</title>
                <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <link rel="stylesheet" href="css/bootstrap.min.css" />
                <link rel="stylesheet" href="css/font-awesome.css" />
                <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
                <link rel="stylesheet" href="css/font-awesome.css" />
                <link rel="stylesheet" href="css/uniform.css" />
                <link rel="stylesheet" href="css/select2.css" />       
                <link rel="stylesheet" href="css/fullcalendar.css" />  
                <link rel="stylesheet" href="css/unicorn.main.css" />
                <link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
        </head>
        <body>
                <div id="header">
                        <h1><a href="./dashboard.php">eyeBuy Admin Panel</a></h1>              
                </div>
                <div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav btn-group">
                <li class="btn btn-inverse" ><a title="" href="#"><i class=" icon-thumbs-up"></i> <span class="text">Bem-vindo <?php echo $_SESSION['user'].'!'; ?></span></a></li>
                <li class="btn btn-inverse" ><?php echo '<a  href="profile.php?user='.$_SESSION['user'].'">';?><i class="icon icon-user"></i> <span class="text">Perfil</span></a></li>
                <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Mensagens</span> <span class="label label-important"><?php echo $admpanel->msgnr($conn); ?></span> <b class="caret"></b></a>
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
                        <a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
                        <ul>
                                <li><a href="dashboard.php"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
                                <li><a href="users.php"><i class="icon icon-user"></i> <span>Utilizadores</span></a></li>
                                <li><a href="categories.php"><i class="icon  icon-th-list"></i> <span>Categorias</span></a></li>
                                <li><a href="products.php"><i class="icon icon-gift"></i> <span>Produtos</span></a></li>
                                <li><a href="tickets.php"><i class="icon icon-warning-sign"></i> <span>Notificações</span> <span class="label" style="background-color: #800;"><?php echo $admpanel->ticketnr($conn); ?></span><span class="label" style="background-color: #f89406;"><?php echo $admpanel->ticketnotsolved($conn); ?></span></a></li>
                                <li class="active"><a href="contentfilter.php"><i class="icon icon-ban-circle"></i> <span>Filtro de conteúdos</span></a></li>
                                <li><a href="setconfig.php"><i class="icon icon-cog"></i> <span>Configurações</span></a></li>
                        </ul>
                </div>
 
 
 
               
                <div id="content">
                        <div id="content-header">
                                <h1>Filtro de Conteúdos</h1>
                        </div>
                        <div id="breadcrumb">
                                <a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
                                <a href="users.php" class="current">Filtro de Conteúdos</a>
                        </div>
                        <div class="container-fluid">
                                <div class="row-fluid">
 
                                </div>
                        </div>
                        <div class="container-fluid">
                                <div class="row-fluid">
                                        <div class="span12">
                                                <?php $admpanel->newFilter($conn); ?>
                                                <div class="widget-box">
                                                        <div class="widget-title">
                                                                <span class="icon">
                                                                        <i class="icon-align-justify"></i>                                                                     
                                                                </span>
                                                                <h5>Criar nova restrição</h5>
                                                        </div>
                                                        <div class="widget-content nopadding">
                                                                <form method="post" class="form-horizontal">
                                                                        <div class="control-group">
                                                                                <label  class="control-label">Palavra(s)</label>
                                                                                <div class="controls">
                                                                                        <input name="newFilter" type="text" /> <button style="margin-left:20px;" name="submit" type="submit" class="btn btn-primary"> Adicionar</button>
                                                                                </div>
                                                                        </div>
                                                                </form>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                                <div class="row-fluid">
                                        <div class="span12">
 
                                                                <?php $admpanel->removeFilter($conn); ?>
                                                <div class="widget-box">
                                                        <div class="widget-title">
                                                       
                                                                <h5>Lista de Filtro de Conteúdos</h5>
                                                        </div>
                                                                        <div class="widget-content">                                                                                                                                                                                                   
                                                                <?php $admpanel->getFilters($conn); ?>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                                <div class="row-fluid">
                                        <div class="span12">
                                                <div class="widget-box">
                                                        <div class="widget-title">
                                                       
                                                                <h5>Lista de Produtos que violam as restrições</h5>
                                                        </div>
                                                        <div class="widget-content">                                                                                                                                                                                           
                                                                <div class="row-fluid"><?php $admpanel->getFiltered($conn); ?></div>
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
            <script src="js/jquery.uniform.js"></script>
            <script src="js/select2.min.js"></script>
            <script src="js/jquery.dataTables.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.tables.js"></script>
            <script src="js/unicorn.dashboard.js"></script>
        </body>
</html>