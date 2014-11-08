<nav>
    <ul>
        <li><a href="/appa/index.php?page=gestion_entreprises" 
				<?php if($_GET['page']=="gestion_entreprises")echo "class='active'"; ?>>
                Gestion des Bars
            </a>
        </li>
        <li><a href="/appa/index.php?page=gestion_webservices" 
				<?php if($_GET['page']=="gestion_webservices")echo "class='active'"; ?>>
                Gestion des Webservices
            </a>
        </li>
        <li><a href="#" class="deco">Déconnexion</a>
			<div class="overlay">
				<div class="popin">
					<p>Voulez vous vraiment vous déconnecter ?</p>
					<a href="/appa/index.php?page=deconnexion">OUI</a>
					<a href="#" class="no">NON</a>
				</div>
			</div>
		</li>
    </ul>
</nav>