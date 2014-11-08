<?php session_start(); ?>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8" />
		<meta name="description" content="description" />
		<meta name="keywords" content="tags" />
        <link rel="stylesheet" media="all" type="text/css" href="css/normalize.css" />
        <link rel="stylesheet" media="all" type="text/css" href="css/common.css" />
        <!--[if lt IE 9]>
            <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <title>QQO / Portail de gestion</title>
    </head>
    
    <body>
    <?php
    if(isset($_SESSION['pseudo_session'])){
        include('inc/portail.php');
    }
    else {
		include('inc/connexion.php');
	}

	if($_SERVER['REQUEST_URI']=="/index.php?page=deconnexion") {
		session_destroy();
		echo '<script type="text/javascript">setTimeout("window.location=\'http://gotenigatien.fr/appa/index.php\'",500);</script>';
	}
	?>
	<div class="footer">Projet de développement mobile</div>
    <script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jQuery.fileinput.js"></script>
	<script src="js/jquery.tablesorter.js"></script>
	
	<script src="js/common.js"></script>
	<script type="text/javascript">
	
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-18736839-1']);
	  _gaq.push(['_setDomainName', 'gzed.fr']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	
	</script>
	
    </body>
</html>