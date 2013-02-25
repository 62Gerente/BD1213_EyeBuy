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




		  


		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
                    ['Ano-Mês', '# Registos'],
                     <?php echo $admpanel->getUserRegistadosData($conn); ?>

        ]);

        var options = {
          colors:['orange'], backgroundColor:'#F9F9F9',vAxis: {title: 'Ano-Mês',   titleTextStyle: {color: 'black'}}
        };

        var chart = new google.visualization.BarChart(document.getElementById('chart_divlocal'));
        chart.draw(data, options);
      }
    </script>
	    <script type="text/javascript">
	      google.load("visualization", "1", {packages:["corechart"]});
	      google.setOnLoadCallback(drawChart);
	      function drawChart() {
	        var data = google.visualization.arrayToDataTable([
	          ['Categorias', 'Número de anúncios'],
	          <?php echo $admpanel->getNumProdutosCategoria($conn); ?>
	        ]);

	       
	        var options = {
	          backgroundColor:'#F9F9F9',
	          chartArea:{left:10,top:10,width:"100%",height:"100%"},
	          is3D:true,
	          'width':600,
              'height':280

	        };

	        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	        chart.draw(data, options);
	      }
	    </script>


	    <script type='text/javascript'>
     google.load('visualization', '1', {'packages': ['geochart']});
     google.setOnLoadCallback(drawMarkersMap);

      function drawMarkersMap() {
      var data = google.visualization.arrayToDataTable([
        ['Localidade',   '# Produtos'],
        <?php echo $admpanel->getNumProdutosLocalidade($conn); ?>

        
      ]);

      var options = {
        region: 'PT',
        backgroundColor:'transparent',
        displayMode: 'markers',
        'width':500,
              'height':400,
        colorAxis: {colors: ['red', 'green']}
      };

      var chart = new google.visualization.GeoChart(document.getElementById('chart_divit'));
      chart.draw(data, options);
    };
    </script>

	</head>
	<body>
		<div id="header">
			<h1><a href="dashboard.php"></a></h1>		
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
				<li class="active"><a href="dashboard.php"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
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
				<h1>Dashboard</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12 center" style="text-align: center;">					
						<ul class="stat-boxes">
							<li>
								<?php if($newusers[2]==0){echo '<div class="left peity_bar_neutral"><span><i class="icon-minus"></i></span>'.round($newusers[2],2).'%</div>';} else{echo '<div class="left peity_bar_good"><span><i class="icon-arrow-up"></i></span>+'.round($newusers[2],2).'%</div>';} ?>
								<div class="right" style="width:150px;">
									<strong><?php echo $newusers[0]; ?> <i class="icon-user"></i></strong>
									Novos utilizadores
								</div>
							</li>
							<li>
								<?php if($anuncios[2]==0){echo '<div class="left peity_bar_neutral"><span><i class="icon-minus"></i></span>'.$anuncios[2].'%</div>';} else{echo '<div class="left peity_bar_good"><span><i class="icon-arrow-up"></i></span>+'.round($anuncios[2],2).'%</div>';} ?>
								<div class="right" style="width:150px;">
									<strong> <?php echo $anuncios[0]; ?> <i class="icon-edit"></i></strong>
									Novos anúncios
								</div>
							</li>
							<li>
								<?php if($mensagens[2]==0){echo '<div class="left peity_bar_neutral"><span><i class="icon-minus"></i></span>'.$mensagens[2].'%</div>';} else{echo '<div class="left peity_bar_good"><span><i class="icon-arrow-up"></i></span>+'.round($mensagens[2],2).'%</div>';} ?>
								<div class="right" style="width:150px;">
									<strong> <?php echo $mensagens[0]; ?> <i class="icon-comment"></i></strong>
									Novas mensagens
								</div>
							</li>
							<li>
								<?php if($transaccoes[2]==0){echo '<div class="left peity_bar_neutral"><span><i class="icon-minus"></i></span>'.$transaccoes[2].'%</div>';} else{echo '<div class="left peity_bar_good"><span><i class="icon-arrow-up"></i></span>+'.round($transaccoes[2],2).'%</div>';} ?>
								<div class="right" style="width:150px;">
									<strong> <?php echo $transaccoes[0]; ?>€ <i class="icon-retweet"></i></strong>
									Novas transacções
								</div>
							</li>
						</ul>
					</div>	
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>Estatísticas gerais</h5></div>
							<div class="widget-content">
								<div class="row-fluid">
								<div class="span4">
									<ul class="site-stats">
										<li> <strong><i class="icon-user"></i> <?php echo '   '.$newusers[1]; ?></strong> <small>Utilizadores registados</small></li>
										<li> <strong><i class="icon-edit"></i> <?php echo '   '.$anuncios[1]; ?></strong> <small>Anúncios colocados</small></li>
										<li> <strong><i class="icon-comment"></i> <?php echo '   '.$mensagens[1]; ?></strong> <small>Mensagens enviadas</small></li>
										<li> <strong><i class="icon-retweet"></i> <?php echo '   '.$transaccoes[1]; ?>€</strong> <small>Dinheiro transaccionado</small></li>
										<li class="divider"></li>
										<li> <strong><i class="icon-warning-sign"></i> <?php echo '  '.$admpanel->getAdmin($conn); ?></strong> <small>Administradores</small></li>
									</ul>
								</div>
								<div class="span8"><center><h4>Distribuição por categorias</h4>
									<div id="chart_div"></div>
									
								</div>	
								</div>							
							</div>
						</div>					
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>Gráficos estatísticos</h5></div>
							<div class="widget-content">
								<div class="row-fluid">
									<div class="span6">
										<center><h4>Registos nos últimos meses</h4></center><br/>
										<div id="chart_divlocal" style="margin-top:-30px;width: 550px; height: 330px;"></div>
									</div>
									<div class="span6">
										<center><h4>Distribuição por localidades</h4></center><br/>
											<div id="chart_divit" style="margin-top:-40px;width: 500px; height: 400px;"></div>
									</div>
								</div>							
							</div>
						</div>					
					</div>
				</div>
				<div class="row-fluid">
					<div class="span4">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-user"></i>
								</span>
								<h5>TOP 7: Melhores vendedores</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="width:65%;">Utilizador</th>
											<th style="width:35%;">Avaliação</th>
										</tr>
									</thead>
									<tbody>
										<?php $admpanel->getTopSellers($conn);?>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-arrow-right"></i>
								</span>
								<h5>TOP 7: Melhores compradores</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="width:65%;">Utilizador</th>
											<th style="width:35%;">Avaliação</th>
										</tr>
									</thead>
									<tbody>
										<?php $admpanel->getTopBuyers($conn);?>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
								<h5>TOP 7: Produtos mais desejados</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>Produto</th>
											<th># vezes desejado</th>
										</tr>
									</thead>
									<tbody>
										<?php $admpanel->getTopWishlist($conn);?>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div><br/><div id="footer" class="span12">
						2013 © Eyebuy Admin Panel. Desenvolvido por André Santos, Daniel Araújo, Daniel Carvalho e Helena Alves.<br/>
						- Eyebuy - Coisas que não te custam os olhos da cara - Eyebuy -
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
