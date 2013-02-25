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
		<link rel="stylesheet" href="css/unicorn.main.css" />
		<link rel="stylesheet" href="css/unicorn.grey.css" class="skin-color" />


		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript">
	      google.load("visualization", "1", {packages:["corechart"]});
	      google.setOnLoadCallback(drawChart);
	      google.setOnLoadCallback(drawChartSell);
	      function drawChart() {
	        var data = google.visualization.arrayToDataTable([
	          ['Categorias', 'Número de transacções'],
	          <?php $admpanel->getbuycats($conn); ?>
	        ]);

	       
	        var options = {
	          title: 'Produtos comprados por categoria',
	          backgroundColor:'#F9F9F9',
	          chartArea:{left:30,top:30,width:"100%",height:"100%"},
	          is3D:true,
	          'width':500,
              'height':300

	        };

	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }


	      	function drawChartSell() {
	        var data = google.visualization.arrayToDataTable([
	          ['Categorias', 'Número de transacções'],
	          <?php $admpanel->getsellcats($conn); ?>
	        ]);

	       
	        var options = {
	          title: 'Produtos vendidos por categoria',
	          backgroundColor:'#F9F9F9',
	          chartArea:{left:30,top:30,width:"100%",height:"100%"},
	          is3D:true,
	          'width':500,
              'height':300

	        };

	        var chart = new google.visualization.PieChart(document.getElementById('chart_div 2'));
	        chart.draw(data, options);
	      }
	    </script>

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
				<h1>Perfil de Utilizador</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
				<a href="users.php">Utilizadores</a>
				<a href="profile.php?user=<?php echo $_GET['user']; ?>" class="current"> Utilizador: <?php echo $_GET['user']; ?></a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<?php $admpanel->banunban($conn); $user=$admpanel->getuser($conn);$admpanel->makeadmin($conn, $user['admin']);$user=$admpanel->getuser($conn); ?>

						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-user"></i></span>
								<h5><?php echo $user['username']; ?><i style="margin-left:30px;" class="icon-certificate"></i> <?php  $nr = $user['nrcompras'] + $user['nrvendas']; echo $nr.' transacções'; ?> <i style="margin-left:30px;" class="icon-tasks"></i> Avaliação geral: </h5> 
								<br/><div  class="progress progress-striped progress-primary" style="margin-top:-11px;width:150px;"><div style="width:<?php $auxperc=round(($user['avalcomp']+$user['avalvend'])/2,2); echo $auxperc; ?>%;" class="bar"><?php echo $auxperc; ?>%</div></div>
							</div>
							<div class="widget-content">
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
									</div>
								<div class="span4" style="margin-top:-18px;">
									<h3><?php echo $user['nome']; ?><?php if ($user['admin']==1){ echo '<span style=";margin-left:20px;" class="label label-success">Admin</span>';} ?><?php if ($user['apagado']==1){ echo '<span style=";margin-left:20px;" class="label label-important">Banido</span>';} ?></h3>
									<p><i class="icon icon-edit"></i> Registado: <?php echo $user['dataregisto']; ?></p>
									<p><i class="icon icon-envelope-alt"></i> Email: <a href="mailto:<?php echo $user['email']; ?>"><?php echo $user['email']; ?></a></p>
									<p><i class="icon icon-phone"></i> Contacto: <?php echo $user['telemovel']; ?></p>
									<p><i class="icon icon-gift"></i> Data de nascimento: <?php echo $user['datanascimento']; ?> (<?php echo $user['idade']; ?> anos)</p>
									</div>	
								<div class="span3"><br/>
									<h5><i class="icon-map-marker"></i> Morada</h5>
									<p><?php echo $user['morada']; ?></p>
									<p><?php echo $user['codigopostal']; ?></p>
									<p><?php echo $user['localidade']; ?></p>
								</div>
								<div class="span2">
									<?php if($user['apagado']==0){echo '<a href="profile.php?user='.$user['username'].'&del=1"><button name="unread" type="submit" class="btn btn-danger btn-mini" style="width:50%;"><i class="icon-white icon-remove"></i> Banir</button></a><br/><br/>';}
									else {echo '<a href="profile.php?user='.$user['username'].'&del=0"><button name="unread" type="submit" class="btn btn-success btn-mini" style="width:50%;"><i class="icon-white icon-ok"></i> Desbanir</button></a><br/><br/>';} ?>
									<?php echo '<a href="newmessage.php?sendto='.$user['username'].'">'; ?><button name="unread" type="submit" class="btn btn-primary btn-mini" style="width:50%;"><i class="icon-white icon-envelope"></i> Mensagem</button><br/><br/>
									<?php echo '<a href="edituser.php?user='.$user['username'].'">'; ?><button name="unread" type="submit" class="btn btn-inverse btn-mini" style="width:50%;"><i class="icon-white icon-pencil"></i> Editar</button></a><br/><br/>
									<?php 
									if($user['admin']==0){
									echo '<a href="profile.php?user='.$user['username'].'&admin=1"><button name="admin" type="submit" class="btn btn-mini" style="width:50%;"><i class="icon-white icon-ok"></i> Admin</button></a>';}
									else{ echo '<a href="profile.php?user='.$user['username'].'&admin=0"><button name="admin" type="submit" class="btn btn-warning btn-mini" style="width:50%;"><i class="icon-white icon-remove"></i> Admin</button></a>';}
								?>
								</div>
								</div>	
								<div class="row-fluid">
									<div class="span1"></div>
								
								<div class="span10">
									<blockquote><?php echo $user['descricao']; ?></blockquote><br/>
									<div class="span5"><center><b>Conta Paypal: </b><?php echo $user['paypal']; ?></center></div><div class="span5"><center><b>Conta MBnet: </b><?php echo $user['mbnet']; ?></center></div>
									<br/>
									<div class="widget-box">
			                            <div class="widget-title">
			                                <ul class="nav nav-tabs">
			                                    <li class="active"><a data-toggle="tab" href="#tab1">Estatísticas de Comprador</a></li>
			                                    <li><a data-toggle="tab" href="#tab2">Estatísticas de Vendedor</a></li>
			                                    <li><a data-toggle="tab" href="#tab3">Montra de produtos</a></li>
			                                    <li><a data-toggle="tab" href="#tab4">Produtos comprados</a></li>
			                                    <li><a data-toggle="tab" href="#tab5">Produtos vendidos</a></li>
			                                </ul>
			                            </div>
			                            <div class="widget-content tab-content">
			                                <div id="tab1" class="tab-pane active">
							                    <div class="span4">
													<ul class="site-stats">
														<li><i class="icon-shopping-cart"></i> <strong><?php echo $user['nrcompras']; ?></strong> <small>compras</small></li>
														<li><i class="icon-tags"></i> <strong><?php echo $user['totalgasto']; ?></strong> <small>€ gastos</small></li>
														<li><i class="icon-thumbs-up"></i> <strong><?php echo $user['avalpos']; ?></strong> <small>avaliações positivas</small></li>
														<li><i class="icon-thumbs-down"></i> <strong><?php echo $user['avalneg']; ?></strong> <small>avaliações negativas</small></li>
														<li class="divider"></li>
														<li><div  class="progress progress-striped progress-success"><div style="width:<?php $auxperc=round(($user['avalcomp']),2); echo $auxperc; ?>%;" class="bar"><?php echo $auxperc; ?>%</div></div><div style="margin-top:-20px;"><center><small>avaliação de comprador</small></center></div></li>
													</ul>
												</div>
												<div class="span8">
													<div id="chart_div" style="width: 600px; height: 300px;"></div>
												</div>	
							                </div>
			                                <div id="tab2" class="tab-pane">
			                               		<div class="span4">
													<ul class="site-stats">
														<li><i class="icon-shopping-cart"></i> <strong><?php echo $user['nrvendas']; ?></strong> <small>vendas</small></li>
														<li><i class="icon-tags"></i> <strong><?php echo $user['totalganho']; ?></strong> <small>€ ganhos</small></li>
														<li><i class="icon-thumbs-up"></i> <strong><?php echo $user['avalposv']; ?></strong> <small>avaliações positivas</small></li>
														<li><i class="icon-thumbs-down"></i> <strong><?php echo $user['avalnegv']; ?></strong> <small>avaliações negativas</small></li>
														<li class="divider"></li>
														<li><div  class="progress progress-striped progress-warning"><div style="width:<?php $auxperc=round(($user['avalvend']),2); echo $auxperc; ?>%;" class="bar"><?php echo $auxperc; ?>%</div></div><div style="margin-top:-20px;"><center><small>avaliação de vendedor</small></center></div></li>
													</ul>
												</div>
												<div class="span8">
													<div id="chart_div 2" style="width: 600px; height: 300px;"></div>
												</div>	
											</div>
			                                <div id="tab3" class="tab-pane">
			                                	<?php $admpanel->getforsale($conn); ?>
			                                </div>
			                                <div id="tab4" class="tab-pane">
			                                	<?php $admpanel->getbought($conn); ?>
			                                </div>
			                                <div id="tab5" class="tab-pane">
			                                	<?php $admpanel->getsold($conn); ?>
			                                </div>
			                            </div>                            
			                        </div>
								</div>	
								<div class="span1"></div>
								</div>													
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
            <script src="js/jquery.flot.pie.min.js"></script>
            <script src="js/jquery.flot.resize.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.charts.js"></script>
	</body>
</html>
