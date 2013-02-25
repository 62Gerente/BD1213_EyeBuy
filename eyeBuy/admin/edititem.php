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
				<h1>Editar Produto</h1>
			</div>
			<div id="breadcrumb">
				<a href="dashboard.php" class="tip-bottom"><i class="icon-home"></i> Dashboard</a>
				<a href="products.php" class="tip-bottom"> Produtos</a>
				<a href="item.php?id=<?php echo $_GET['id']; ?>" class="current"> Produto id: <?php echo $_GET['id']; ?></a>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<?php $admpanel->edititem($conn); $item=$admpanel->getItem($conn); ?>

						<form method="post">
						<div class="widget-box">
							<div class="widget-title"><span class="icon"><i class="icon-tags"></i></span>
								<h5>Produto id: <?php echo $item['id']; ?><span style="margin-top:-3px;margin-left:20px;" class="label label-info"><?php echo $item['metodovenda']; ?></span><?php if ($item['apagado']==1){ echo '<span style="margin-top:-3px;margin-left:20px;" class="label label-important">Apagado</span>';} ?><?php if ($item['datavenda']!=null){ echo '<span style="margin-top:-3px;margin-left:20px;" class="label label-success">Vendido</span>';} ?></h5>
							</div>
							<div class="widget-content">
								<div class="row-fluid">
									<div class="span1"></div>
									<div class="span2">
										<ul class="thumbnails" >
											<li class="span2" style="min-height:180px;min-width:180px;">
												<a class="thumbnail">
													<?php 
														if($item['imagem'] != null){
															$img = $item['imagem']->load();
															print('<img style="max-height:170px;max-width:170px;"  src="data:image/png;base64,'.base64_encode($img).'" />'); 
														}
														else {
															print('<img style="height:150px;width:150px;"  src="img/item.png" />'); 
														}
													?>
												</a>
											</li>
										</ul>
									</div>
								<div class="span4" style="margin-top:-23px;overflow:visible">
									<h3><input type="text" name="nome" value="<?php echo $item['nome']; ?>" style="margin-bottom:0px;width:300px;"></h3>
									<p><i class="icon icon-user"></i> Vendedor: <input name="vendedor" type="text" value="<?php echo $item['vendedor']; ?>" style="margin-bottom:0px;width:215px;"></p>
									<p><i class="icon icon-time"></i> Colocado em: <input style="margin-bottom:0px;width:192px;"  name="datacolocacao" type="text" data-date="<?php echo $item['datacolocacao']; ?>" data-date-format="dd-mm-yyyy" value="<?php echo $item['datacolocacao']; ?>" class="datepicker" /></p>
									<p><i class="icon icon-file"></i> Estado: <input type="text" name="estado" value="<?php echo $item['estado']; ?>" style="margin-bottom:0px;width:235px;"></p>
									
									</div>	
								<div class="span5" style="margin-top:-33px;overflow:visible"><br/>
									<p><i class="icon-th-list"></i> Categoria: <select name="categoria"><?php $admpanel->getitemcategorias($conn,$item); ?></select></p>
									<p><i class="icon "></i> Método venda: <select name="metvenda" style="width:210px;"><option>Leilão</option><option>Venda Directa</option></select></p>
									<p><i class="icon-tags"></i> Preço: <input type="text" name="preco" value="<?php echo $item['preco']; ?>" style="margin-bottom:0px;"></p>
									<p><i class=" icon-th-large"></i> Quantidade: <input type="text" name="quantidade" value="<?php echo $item['quantidade']; ?>" style="margin-bottom:0px;max-width:70px;" ></p>
									
								</div>
								</div>	
								<div class="row-fluid">
									<div class="span1"></div>
								
								<div class="span10">
									<blockquote><textarea style="width:100%;" name="descricao"><?php echo $item['descricao']; ?></textarea></blockquote>
									<br/>
									<center><button name="submit" type="submit" class="btn btn-primary"><i class="icon-white icon-save"></i> Guardar</button>
									<button name="unread" type="submit" class="btn btn-danger"><i class="icon-white icon-circle-arrow-left"></i> <a href="item.php?id=<?php echo $_GET['id']; ?>">Voltar ao produto</a></button></center>	<br/>
								</div>	
								<div class="span1"></div>
								</div>													
							</div>
						</div>	</form>
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
