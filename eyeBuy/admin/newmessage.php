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
		<link rel="stylesheet" href="css/uniform.css" />
		<link rel="stylesheet" href="css/select2.css" />		
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />
		<link rel="stylesheet" href="css/jquery.gritter.css" />
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
				<li><a href="contentfilter.php"><i class="icon icon-ban-circle"></i> <span>Filtro de conteúdos</span></a></li>
				<li><a href="setconfig.php"><i class="icon icon-cog"></i> <span>Configurações</span></a></li>
			</ul>
		</div>
		
		
		<div id="content">
			<div id="content-header">
				<h1>Nova mensagem</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i>Dashboard</a>
				<a href="newmessage.php" class="current">Nova Mensagem</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<?php $admpanel->newmessage($conn); ?>
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-envelope"></i>									
								</span>
								<h5>Nova Mensagem</h5>
							</div>
							<div class="widget-content nopadding">

								<form method="post" class="form-horizontal">
									<div class="control-group">
										<label class="control-label">Enviar para</label>
										<div class="controls">
											<select name="user[]" style="width:80%;" multiple>
												<option>Todos</option>
												<?php if(isset($_GET['sendto'])){ echo '<option selected>'.$_GET['sendto'].'</option>';} ?>
												<?php $admpanel->usersmsg($conn); ?>
											</select>
											<!-- <span class="help-block">Para vários utilizadores introduza os nomes separados por ';'. Para enviar para todos introdua 'Todos'.</span>-->
										</div>
									</div>									
									<div class="control-group">
										<label class="control-label">Assunto</label>
										<div class="controls">
											<input name="assunto" type="text">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" >Mensagem</label>
										<div class="controls">
											<textarea name="mensagem" style="height: 225px;"></textarea>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" name="submit" class="btn btn-primary">Enviar</button>
									</div>
								</form>
							</div>
						</div>						
					</div>
				</div>
			</div>
		</div>
		

            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.uniform.js"></script>
            <script src="js/select2.min.js"></script>
            <script src="js/jquery.dataTables.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.tables.js"></script>

	</body>
</html>
