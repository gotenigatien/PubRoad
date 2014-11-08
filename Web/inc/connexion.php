<div id="wrapper">
    <header id="header">
        <h1><a href="#">Administration de QQO</a></h1>
    </header>
    <section id="connexion">
        <h2>Connexion sécurisée</h2>
        <form id="connex" action="#" method="post">
<div id='myform_login_errorloc' class="error_strings"></div>
            <input type="text" placeholder="Login" name="login" size="15" MAXLENGTH="15" >
            <input type="password" placeholder="Mot de passe" name="password" size="15" MAXLENGTH="15" >
            <input type="hidden" name="verif_submit" value="ok">
            <input type="submit" value="Se connecter">
        </form>
    </section>
</div>


<script src="js/gen_validatorv4.js"></script>
<script language="JavaScript" type="text/javascript" xml:space="preserve">
var frmvalidator  = new Validator("connex");
frmvalidator.EnableOnPageErrorDisplay();
frmvalidator.EnableMsgsTogether();
frmvalidator.addValidation("login","req","Please enter your First Name");
frmvalidator.addValidation("login","maxlen=20",	"Max length for FirstName is 20");
</script>


<?php
if(@$_POST['verif_submit']=="ok"){
	include 'dbConf/db_connect.php';
	$db = new DB_CONNECT();
	function verif_util_mdp($login,$password)
	{	
		$result = mysql_query('SELECT admin_login FROM ann_admin 
							   WHERE admin_login="'.$login.'" 
							   AND admin_pass="'.md5($password).'"');
		if (mysql_num_rows($result) == 1)
		{
			$_SESSION['pseudo_session'] = $result;
			echo '<script type="text/javascript">setTimeout("window.location=\'http://gotenigatien.fr/appa/\'",500);</script>';
		}
		else { ?>
			<script type="text/javascript">alert("Mauvais mot de passe ou login, retry or die.");</script>

		<?php }
	}
	verif_util_mdp($_POST['login'],$_POST['password']);
} 

?>