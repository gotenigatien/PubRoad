<?php
 
include('DAL/entreprise.php');
 
function getBpListOfEntreprise(){
    $result = getListOfEntreprise();
     
    while ($row = mysql_fetch_array($result))
    {
    echo '<tr>
            <td class="libelle" style="display:none">'.$row['Entreprise'].'</td>
            <td class="entre">'.$row['Entreprise'].'
                    <div class="relati"><div class="hover">
                        <a href="#" class="linksingle">&nbsp;</a>
                        <a href="#" class="edit">Modifier</a>
                        <a href="#" class="delete">X</a>
                    </div></div>
                    <div class="overlaysingle">
                    <div class="popinsingle">
                        <div class="single">
                            <div class="clearfix">
                                <div class="photo">
                                    <img src="/appa/Photo/'.$row['Photo'].'" width="120" height="120">
                                </div>
                                <div class="right">
                                    <h3>'.$row['Entreprise'].' </h3>
                                    <h3>'.$row['Name'].' </h3>
                                    <div class="details clearfix">
                                        <div class="adresse">
                                            '.$row['Adresse'].'<br/>
                                            '.$row['CodePostal'].' '.$row['Ville'].'
                                        </div>
                                        <div class="tel">
                                            '.$row['Tel'].'<br/>
                                            '.$row['Fax'].'<br/><br/>
                                            '.$row['Email'].'<br/>
                                            '.$row['SiteWeb'].'
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hours">
                                '.$row['Horaires'].'
                            </div>
                            <div class="descrip">
                                '.$row['Description'].'
                            </div>
                        </div>
                        <a href="#" class="no">FERMER LA FENETRE</a>
                    </div>
                </div>
     
                <div class="overlay">
                <div class="deletesure">
                    <p>Etes-vous sur de vouloir supprimer '.$row['Entreprise'].' ?</p>
                    <a href="/appa/index.php?page=gestion_entreprises&id_supp='.$row['ID'].'">OUI</a>
                    <a href="#" class="no">NON</a>
                </div>
                </div>
            </td>
            <td >   <h3>'.$row['Name'].' </h3></td>
            <td class="adresse">'.$row['Adresse'].'</td>
            <td class="codepostal">'.$row['CodePostal'].'</td>
            <td class="ville">'.$row['Ville'].'</td>
            <td class="tel">'.$row['Tel'].'</td>
            <td class="fax">'.$row['Fax'].'</td>
            <td class="email">'.$row['Email'].'</td>
            <td class="siteweb">'.$row['SiteWeb'].'</td>
            <td class="plageHoraire" id="hidden">'.$row['Horaires'].'</td>
            <td class="description" id="hidden">'.$row['Description'].'</td>
            <td class="id" id="hidden">'.$row['ID'].'</td>
            <td class="file" id="hidden">Fichier</td>
            <td class="pt"><a href="#" class="edit">Modifier</a></td>
            <td class="pt"><a href="#" class="delete">X</a></td>
         </tr>';
    }
}
 
 
function getBpListOfEntrepriseByParam($who, $where){
     
    $result = getListOfEntrepriseByParam($who, $where);
    while ($row = mysql_fetch_array($result))
    {
    echo '<tr>
            <td class="libelle">'.$row['Entreprise'].'
                    <div class="relati"><div class="hover">
                        <a href="#" class="linksingle">&nbsp;</a>
                        <a href="#" class="edit">Modifier</a>
                        <a href="#" class="delete">X</a>
                    </div></div>
                    <div class="overlaysingle">
                    <div class="popinsingle">
                        <div class="single">
                            <div class="clearfix">
                                <div class="photo">
                                    <img src="../Photo/'.$row['Photo'].'" width="120" height="120">
                                </div>
                                <div class="right">
                                    <h3>'.$row['Entreprise'].' </h3>
                                    <h3>'.$row['Name'].' </h3>
                                    <div class="details clearfix">
                                        <div class="adresse">
                                            '.$row['Adresse'].'<br/>
                                            '.$row['CodePostal'].' '.$row['Ville'].'
                                        </div>
                                        <div class="tel">
                                            '.$row['Tel'].'<br/>
                                            '.$row['Fax'].'<br/><br/>
                                            '.$row['Email'].'<br/>
                                            '.$row['SiteWeb'].'
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="hours">
                                '.$row['Horaires'].'
                            </div>
                            <div class="descrip">
                                '.$row['Description'].'
                            </div>
                        </div>
                        <a href="#" class="no">FERMER LA FENETRE</a>
                    </div>
                </div>
     
                <div class="overlay">
                <div class="deletesure">
                    <p>Etes-vous sur de vouloir supprimer '.$row['Entreprise'].' ?</p>
                    <a href="/appa/index.php?page=gestion_entreprises&id_supp='.$row['ID'].'">OUI</a>
                    <a href="#" class="no">NON</a>
                </div>
                </div>
            </td>
            <td class="adresse">'.$row['Adresse'].'</td>
            <td class="codepostal">'.$row['CodePostal'].'</td>
            <td class="ville">'.$row['Ville'].'</td>
            <td class="tel">'.$row['Tel'].'</td>
            <td class="fax">'.$row['Fax'].'</td>
            <td class="email">'.$row['Email'].'</td>
            <td class="codepostal">'.$row['Name'].'</td>
            <td class="siteweb">'.$row['SiteWeb'].'</td>
            <td class="plageHoraire" id="hidden">'.$row['Horaires'].'</td>
            <td class="description" id="hidden">'.$row['Description'].'</td>
            <td class="id" id="hidden">'.$row['ID'].'</td>
            <td class="file" id="hidden">Fichier</td>
            <td class="pt"><a href="#" class="edit">Modifier</a></td>
            <td class="pt"><a href="#" class="delete">X</a></td>
         </tr>';
    }   
}