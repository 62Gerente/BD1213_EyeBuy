 <?php
    
      include_once('includes/functions.php');
      require_once('config.php');
      $admpanel = new adminpanel(DBHOST,DBUSER,DBPASS,DBCHRSET);

      $conn= $admpanel->connect();
      $admpanel->authcheck();
      $msg=$admpanel->getmsg($conn);

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
				<h1>Mensagem enviada</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i>Dashboard</a>
				<a href="outbox.php" class="current">Mensagens enviadas</a>
				<a href="message.php?id=<?php echo $msg['id']; ?>" class="current"><?php echo $msg["assunto"]; ?></a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="span10">
							<div class="widget-box">
								<div class="widget-title">
									<span class="icon">
										<i class="icon-comment"></i>
									</span>
										<h5>Para: <?php echo $msg["destino"]; ?>              Assunto: <?php echo $msg["assunto"]; ?></h5> <div class="pull-right"><h5> <?php echo $msg["time"]; ?></h5>  </div>
									</div>
								<div class="widget-content" style="min-height: 80px;">
										<?php echo $msg["mensagem"]; ?>
								</div>
							</div>
						</div>
						<div class="span2"><br/><form method="post" action="<?php $admpanel->newreply($conn,$msg["destino"]); ?>"><button name="newreply" type="submit" class="btn btn-inverse" style="width:100%;"><i class="icon-white icon-retweet"></i>  Nova resposta</button> </form> 
						<br/><form method="post" action="<?php $admpanel->deletemsg($conn); ?>"><button name="delete" type="submit" class="btn btn-danger" style="width:100%;"><i class="icon-white icon-remove"></i>  Apagar</button> </form> 
						<br/><a href="#collapseTwo" data-toggle="collapse" ><button class="btn btn-primary" style="width:100%;"><i class="icon-pencil icon-white"></i> Encaminhar</button></a>
						</div>
																		<div class="widget-box collapsible">
                            <div class="widget-title">
                                <a href="#collapseTwo" data-toggle="collapse" class="collapsed">
    								<span class="icon"><i class="icon-pencil"></i></span>
                                    <h5>Encaminhar mensagem</h5>
                                </a>
                            </div>
	                            <div <?php $admpanel->replyopen(); ?> >
	        						<div class="widget-content nopadding">
										<form method="post" class="form-horizontal">
											<div class="control-group">
												<label class="control-label">Enviar para:</label>
												<div class="controls">
												<select name="users[]" style="width:80%;" multiple>
													<option>Todos</option>
													<?php echo '<option selected>'.$msg["destino"].'</option>'; ?>
													<?php $admpanel->usersmsg($conn); ?>
												</select>

												</div>
											</div>											
											<div class="control-group">
												<label class="control-label">Assunto</label>
												<div class="controls">
													<input type="text" name="assunto" value="<?php echo $msg["assunto"]; ?>">
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">Mensagem</label>
												<div class="controls">
													<textarea name="mensagem" style="height: 100px;"><?php echo $msg["mensagem"]; ?></textarea>
												</div>
											</div>
											<div class="form-actions" align="center">
												<button type="submit" class="btn btn-success"><i class="icon-share-alt icon-white"></i>Enviar</button>
											</div><?php $admpanel->msgenc($conn); ?>
										</form>
									</div>
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
