    <?php
        include_once('includes/functions.php');
        require_once('config.php');
        $admpanel = new adminpanel(DBHOST,DBUSER,DBPASS,DBCHRSET);

        $conn= $admpanel->connect(); 
        $admpanel->cleansessions(); 
    ?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>eyeBuy Admin Panel Login</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/font-awesome.css" />
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/unicorn.login.css" />
        <link rel="stylesheet" href="css/unicorn.main.css" />
        <link rel="stylesheet" href="css/jquery.gritter.css" />
    </head>
    <body> 

   


<script type="text/javascript"></script>

        <div id="logo" style="margin-top:13%;">
            <img src="img/logo2.png" alt="" />
        </div>
        <div id="loginbox" >            
            <form id="loginform" class="form-vertical" method="post" action=" <?php $admpanel->login($conn) ?>">
				<p>Introduza o nome de administrador e password.</p>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><input name="username" type="text" id="username"  placeholder="Nome de utilizador" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-lock"></i></span><input name="password" type="password" id="password" placeholder="Password" />
                        </div>
                    </div>
                </div>
                <div class="form-actions" style="border-radius:7px;">
                    <span class="pull-right"><input type="submit" name="Submit" class="btn btn-inverse" value="Login" /></span>
                </div>
            </form>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.ui.custom.js"></script>
        <script src="js/jquery.gritter.min.js"></script>
        <script src="js/jquery.peity.min.js"></script> 
        <script src="js/unicorn.login.js"></script> 

        <?php $admpanel->wronglogin(); ?>

    </body>
</html>
