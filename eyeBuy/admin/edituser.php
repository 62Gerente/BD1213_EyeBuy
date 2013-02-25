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
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/colorpicker.css" />
        <link rel="stylesheet" href="css/datepicker.css" />
		<link rel="stylesheet" href="css/uniform.css" />
		<link rel="stylesheet" href="css/select2.css" />	
		<link rel="stylesheet" href="css/font-awesome.css" />			
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
				<li class="active"><a href="users.php"><i class="icon icon-user"></i> <span>Utilizadores</span></a></li>
				<li><a href="categories.php"><i class="icon  icon-th-list"></i> <span>Categorias</span></a></li>
				<li><a href="products.php"><i class="icon icon-gift"></i> <span>Produtos</span></a></li>
				<li><a href="tickets.php"><i class="icon icon-warning-sign"></i> <span>Notificações</span> <span class="label" style="background-color: #800;"><?php echo $admpanel->ticketnr($conn); ?></span><span class="label" style="background-color: #f89406;"><?php echo $admpanel->ticketnotsolved($conn); ?></span></a></li>
				<li><a href="contentfilter.php"><i class="icon icon-ban-circle"></i> <span>Filtro de conteúdos</span></a></li>
				<li><a href="setconfig.php"><i class="icon icon-cog"></i> <span>Configurações</span></a></li>
			</ul>
		</div>
		
		
		<div id="content">
			<div id="content-header">
				<h1>Editar Utilizador</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
				<a href="users.php" class="tip-bottom"> Utilizadores</a>
				<a href="profile.php?user=<?php echo $_GET['user']; ?>" class="current"> Utilizador: <?php echo $_GET['user']; ?></a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">

						<?php $admpanel->edituser($conn); $user=$admpanel->getuser($conn); ?>

						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-user"></i></span>
								<h5><?php echo $user['username']; ?><i style="margin-left:30px;" class="icon-certificate"></i> <?php  $nr = $user['nrcompras'] + $user['nrvendas']; echo $nr.' transacções'; ?> <i style="margin-left:30px;" class="icon-tasks"></i> Avaliação geral: </h5> 
								<br/><div  class="progress progress-striped progress-primary" style="margin-top:-11px;width:150px;"><div style="width:<?php $auxperc=round(($user['avalcomp']+$user['avalvend'])/2,2); echo $auxperc; ?>%;" class="bar"><?php echo $auxperc; ?>%</div></div>
							</div>
							<div class="widget-content">
								<form  method="post" class="form-horizontal">
								<div class="row-fluid">

									<div class="span1"></div>
									<div class="span2">
										<ul class="thumbnails" >
											<li class="span2" style="height:150px;width:150px;">
												<a class="thumbnail">
													<?php 
														if($user['imagem'] != null){
															$img = $user['imagem']->load();
															print('<img style="height:150px;width:150px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
														}
														else {
															print('<img style="height:150px;width:150px;"  src="img/user.png" />'); 
														}
													?>
												</a>
											</li>
										</ul>

												<input name="imagem" type="file" />
									</div>
								<div class="span4" style="margin-top:-18px;">
									<h4><input style="font-size: 20px;" name="nome" value="<?php echo $user['nome']; ?>" type="text" /><?php if ($user['apagado']==1){ echo '<span style=";margin-left:20px;" class="label label-important">Banido</span>';} ?></h4>
									<p><i class="icon icon-edit"></i> Registado: <input style="width:197px;" name="dataregisto" type="text" data-date="<?php echo $user['dataregisto']; ?>" data-date-format="dd-mm-yyyy" value="<?php echo $user['dataregisto']; ?>" class="datepicker" /></p>
									<p><i class="icon icon-envelope-alt"></i> Email: <input style="width:226px;" name="email" value="<?php echo $user['email']; ?>" type="text" /></p>
									<p><i class="icon icon-phone"></i> Contacto: <input style="width:206px;" name="contacto" value="<?php echo $user['telemovel']; ?>" type="text" /></p>
									<p><i class="icon icon-gift"></i> Data de nascimento: <input style="width:133px;" name="datanascimento" type="text" data-date="<?php echo $user['datanascimento']; ?>" data-date-format="dd-mm-yyyy" value="<?php echo $user['datanascimento']; ?>" class="datepicker" /></p>
									</div>	
								<div class="span4"><br/>
									<h5><i class="icon-map-marker"></i> Morada</h5>
									<p>Endereço <input style="width:230px;" name="morada" value="<?php echo $user['morada']; ?>" type="text" /></p>
									<p>Código postal <input style="width:205px;" name="codpostal" value="<?php echo $user['codigopostal']; ?>" type="text" /></p>
									<p>Distrito <select name="localidade" style="width:250px;"><option selected><?php echo $user['localidade']; ?></option><?php $admpanel->localidades($conn); ?></select></p>
								</div>
								<div class="span1">
								</div>
								</div>	
								<div class="row-fluid">
									<div class="span1"></div>
								
								<div class="span10">
									<br/><blockquote><textarea style="width:100%;" name="descricao"><?php echo $user['descricao']; ?></textarea></blockquote><br/>
									<div class="span5"><b>Conta Paypal: </b><input style="width:250px;" name="paypal" value="<?php echo $user['paypal']; ?>" type="text" /></div>
									<div class="span5"><b>Conta MBnet: </b><input style="width:250px;" name="mbnet" value="<?php echo $user['mbnet']; ?>" type="text" /></div>
									<br/><br/><br/>
									<div class="span5"><b>Password: </b><input style="width:270px;" name="password" type="password" /></div>
									<div class="span5"><b>Confirmar Password: </b><input style="width:200px;" name="checkpassword"  type="password" /></div>
									<br/><br/><br/><br/>

									<center><button name="submit" type="submit" class="btn btn-primary"><i class="icon-white icon-save"></i> Guardar</button>
									<a href="profile.php?user=<?php echo $_GET['user']; ?>"><button class="btn btn-danger"><i class="icon-white icon-circle-arrow-left"></i> <a href="profile.php?user=<?php echo $_GET['user']; ?>">Voltar ao perfil</a></button></a></center>	<br/>
								<div class="span1"></div>
								</div>													
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
            <script src="js/bootstrap-colorpicker.js"></script>
            <script src="js/bootstrap-datepicker.js"></script>
            <script src="js/jquery.uniform.js"></script>
            <script src="js/select2.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.form_common.js"></script>
	</body>
</html>
