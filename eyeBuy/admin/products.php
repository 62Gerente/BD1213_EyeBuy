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
		<link rel="stylesheet" href="css/jquery.gritter.css" />
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
				<li class="active"><a href="products.php"><i class="icon icon-gift"></i> <span>Produtos</span></a></li>
				<li><a href="tickets.php"><i class="icon icon-warning-sign"></i> <span>Notificações</span> <span class="label" style="background-color: #800;"><?php echo $admpanel->ticketnr($conn); ?></span><span class="label" style="background-color: #f89406;"><?php echo $admpanel->ticketnotsolved($conn); ?></span></a></li>
				<li><a href="contentfilter.php"><i class="icon icon-ban-circle"></i> <span>Filtro de conteúdos</span></a></li>
				<li><a href="setconfig.php"><i class="icon icon-cog"></i> <span>Configurações</span></a></li>
			</ul>
		</div>
		
		
		<div id="content">
			<div id="content-header">
				<h1>Lista de produtos</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
				<a href="products.php" class="current">Lista de produtos</a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-search"></i></span>
								<h5>Pesquisa de produtos</h5> 
								
							</div>
							<div class="widget-content"><form  method="post">
								<div class="row-fluid">
									<div class="span4">
										<p><i class="icon icon-search"></i> Procurar: <input value="<?php if(isset($_POST['procura'])){ echo $_POST['procura'];} ?>" style="width:260px;margin-bottom: 0px;" name="procura" type="text" /></p><br/>
										<p>
											<div class="control-group" style="margin-left:15px; margin-top:-25px;">
											<label class="control-label">Método de venda</label>
												<div class="controls">
													<label><input <?php if(isset($_POST['radios']) && $_POST['radios']=="Leilão"){ echo "checked";} ?> type="radio" id="r1" name="radios" value="Leilão" style="margin-top:-2px;opacity:1;" /> Leilão </label>
													<label><input <?php if(isset($_POST['radios']) && $_POST['radios']=="Venda directa"){ echo "checked";} ?> type="radio" id="r2" name="radios" value="Venda directa" style="opacity:1;margin-top:-2px;" /> Venda directa </label>
													<label><input <?php if(isset($_POST['radios']) && $_POST['radios']=="Vendido"){ echo "checked";} ?> type="radio" id="r3" name="radios" value="Vendido" style="margin-top:-2px;opacity:1;" /> Vendido </label>
												</div>
											</div>
									</div>
									<div class="span4">
										<p><i class="icon icon-th-list"></i> Categoria <select name="categoria" style="width:255px;margin-left:10px;">
												<option></option>
												<?php $admpanel->getcategorias($conn);?>
											</select>
										</p><br/>
										<p><i class="icon icon-user"></i> Vendido por: <input value="<?php if(isset($_POST['vendedor'])){ echo $_POST['vendedor'];} ?>" style="width:240px;margin-bottom: 0px;" name="vendedor" type="text" /></p><br/>
										<p><i class="icon icon-user"></i> Vendido a: <input value="<?php if(isset($_POST['comprador'])){ echo $_POST['comprador'];} ?>" style="width:254px;margin-bottom: 0px;" name="comprador" type="text" /></p><br/>
									</div>	
									<div class="span4">
										<p><i class="icon icon-info-sign"></i> Pagamento: <select name="metpay" style="width:250px;margin-left:10px;">
												<option></option>
												<option <?php if(isset($_POST['metpay']) && $_POST['metpay']=="Paypal"){ echo "selected";} ?> >Paypal</option>
												<option <?php if(isset($_POST['metpay']) && $_POST['metpay']=="MBNet"){ echo "selected";} ?> >MBNet</option>
											</select>
										</p><br/>
										<p><i class="icon icon-map-marker"></i> Localidade <select name="localidade" style="width:263px;margin-left:10px;">
												<option></option>
												<?php $admpanel->getlocalidades($conn);?>
											</select>
										</p><br/>
										<p><i class="icon icon-edit"></i> Preço entre: <input value="<?php if(isset($_POST['minprice'])){ echo $_POST['minprice'];} ?>" style="width:75px;margin-bottom: 0px;" name="minprice" type="text" /> e <input value="<?php if(isset($_POST['maxprice'])){ echo $_POST['maxprice'];} ?>" style="width:75px;margin-bottom: 0px;" name="maxprice" type="text" /></p><br/>
											
										</p>
									</div>	
								</div>
									<center><button type="submit" class="btn btn-primary">Procurar</button></center>
								<div class="row-fluid">
									<div class="span12">
									</div>													
								</div>
							</div>	
							</form>
						</div>
						<div class="row-fluid">
								<div class="span12">
									<div class="widget-box">
										<div class="widget-title">
										
											<h5>Resultado da pesquisa</h5>
										</div>
										<div class="widget-content nopadding">
											<table class="table table-bordered data-table" style="font-size:14px;">
												<thead>
												<tr>
												<th></th>
												</tr>
												</thead>
												
												<tbody>
													<?php $admpanel->getprodutos($conn);?>
												</tbody>
												</table>  
										</div>
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
            <script src="js/bootstrap-colorpicker.js"></script>
            <script src="js/bootstrap-datepicker.js"></script>
            <script src="js/jquery.uniform.js"></script>
            <script src="js/select2.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/jquery.dataTables.min.js"></script>
            <script src="js/unicorn.js"></script>
            <script src="js/unicorn.tables.js"></script>
            <script src="js/unicorn.form_common.js"></script>
            <script src="js/unicorn.interface.js"></script>
            
	</body>

</html>
