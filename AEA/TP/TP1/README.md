#Projet 1 AeA

#Claire Hunter & Antoine CANDA

#Usage

Pour une exécution automatique de quelques exemples, utilisez le script fourni (script.sh) qui produit les
fichiers .dat et .png correspondants.

Si vous voulez générer un fichier .dat manuellement, avec les arguments que vous voulez, voici des détails
sur la commande à exécuter :

java -jar aea.jar <mode1> <mode2> <seqADN> <arg> <nomFichierDotPlot>

avec :

<mode1> =

			-man pour une entree de sequence a chercher manuellement 
			<arg> est la dite sequence en tenant compte du fait que dans la sequence de base il n'y a pas de T mais U a la place

			-s pour une entree de sequence a chercher sous forme de second fichier fasta
			<arg> est le nom du fichier 

			-e pour une recherche selon la sequence elle meme en considerant des mots de taille N
			<arg> est la taille N des mots a considerer.
				
<mode2> =

			-0 pour une recherche uniquement en considerant la sequence entree
			-1 pour une recherche avec la sequence entree et son complementaire
			-2 pour une recherche avec la sequence entree et son reverse
			-3 pour une recherche avec la sequence entree et son reverse complementaire
			-4 pour une recherche avec la sequence entree, son reverse et son complementaire
			-5 pour une recherche avec la sequence entree, son reverse et son reverse complementaire
			-6 pour une recherche avec la sequence entree, son complementaire et son reverse complementaire
			-7 pour une recherche avec la sequence entree, son reverse, son complementaire et son reverse complementaire
			
<seqADN> = nom du fichier contenant la sequence ARN ou ADN dans laquelle faire les recherches

<nomFichierDotPlot> = nom du fichier a creer et contenant les coordonnees necessaires pour faire le DotPlot