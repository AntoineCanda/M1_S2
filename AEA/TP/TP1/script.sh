#!/bin/bash

aea='java -jar aea.jar'
chromosome='chromosome13_NT_009952.14.fasta'
prmiarn='hsa-miR-17-5p_preMI_ARN.fasta'
arn='ARNmessager-1.fasta'

nom='chrom3-n8-7'
nom1='chrom3-n8-0'
nom2='chrom3-n10-7'
nom3='chrom3-n10-0'
nom4='chrom3-n12-7'
nom5='chrom3-n12-0'
nom6='chrom3-n15-7'
nom7='chrom3-n15-0'
nom8='hsa-n5-7'
nom9='hsa-n5-0'
nom10='hsa-n8-7'
nom11='hsa-n8-0'
nom12='arn1-n5-7'
nom13='arn1-n5-0'
nom14='arn1-n10-7'
nom15='arn1-n10-0'
nom16='chrom3N10ReverseComplementaire'
nom17='arn1N5ReverseComplementaire'

echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 8 et mode 7"
$aea -e -7 $chromosome 8 $nom.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 8 et mode 0"
$aea -e -0 $chromosome 8 $nom1.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 10 et mode 7"
$aea -e -7 $chromosome 10 $nom2.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 10 et mode 0"
$aea -e -0 $chromosome 10 $nom3.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 12 et mode 7"
$aea -e -7 $chromosome 12 $nom4.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 12 et mode 0"
$aea -e -0 $chromosome 12 $nom5.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 15 et mode 7"
$aea -e -7 $chromosome 15 $nom6.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 15 et mode 0"
$aea -e -0 $chromosome 15 $nom7.dat
echo "Calcul des doublons de tout les mots pour le code arn de hsa-miR-17-5p_preMI_ARN avec une taille de mot de 5"
$aea -e -7 $prmiarn 5 $nom8.dat
echo "Calcul des doublons de tout les mots pour le code arn de hsa-miR-17-5p_preMI_ARN avec une taille de mot de 5"
$aea -e -0 $prmiarn 5 $nom9.dat
echo "Calcul des doublons de tout les mots pour le code arn de hsa-miR-17-5p_preMI_ARN avec une taille de mot de 8"
$aea -e -7 $prmiarn 8 $nom10.dat
echo "Calcul des doublons de tout les mots pour le code arn de hsa-miR-17-5p_preMI_ARN avec une taille de mot de 8"
$aea -e -0 $prmiarn 8 $nom11.dat
echo "Calcul des doublons de tout les mots pour le code arn de ARNmessager-1 avec une taille de mot de 5"
$aea -e -7 $arn 5 $nom12.dat
echo "Calcul des doublons de tout les mots pour le code arn de ARNmessager-1 avec une taille de mot de 5"
$aea -e -0 $arn 5 $nom13.dat
echo "Calcul des doublons de tout les mots pour le code arn de ARNmessager-1 avec une taille de mot de 10"
$aea -e -7 $arn 10 $nom14.dat
echo "Calcul des doublons de tout les mots pour le code arn de ARNmessager-1 avec une taille de mot de 10"
$aea -e -0 $arn 10 $nom15.dat
echo "Calcul des doublons de tout les mots pour le chromosome13_NT_009952.14 avec une taille de mot de 10 et mode 3"
$aea -e -3 $chromosome 10 $nom16.dat
echo "Calcul des doublons de tout les mots pour le code arn de ARNmessager-1 avec une taille de mot de 5"
$aea -e -3 $arn 5 $nom17.dat

echo set terminal png > scriptDotPlot.gnu
echo set output \"$nom.png\" >> scriptDotPlot.gnu
echo plot \""$nom.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom1.png\" >> scriptDotPlot.gnu
echo plot \""$nom1.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom2.png\" >> scriptDotPlot.gnu
echo plot \""$nom2.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom3.png\" >> scriptDotPlot.gnu
echo plot \""$nom3.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom4.png\" >> scriptDotPlot.gnu
echo plot \""$nom4.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom5.png\" >> scriptDotPlot.gnu
echo plot \""$nom5.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom6.png\" >> scriptDotPlot.gnu
echo plot \""$nom6.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom7.png\" >> scriptDotPlot.gnu
echo plot \""$nom7.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom8.png\" >> scriptDotPlot.gnu
echo plot \""$nom8.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom9.png\" >> scriptDotPlot.gnu
echo plot \""$nom9.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom10.png\" >> scriptDotPlot.gnu
echo plot \""$nom10.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom11.png\" >> scriptDotPlot.gnu
echo plot \""$nom11.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom12.png\" >> scriptDotPlot.gnu
echo plot \""$nom12.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom13.png\" >> scriptDotPlot.gnu
echo plot \""$nom13.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom14.png\" >> scriptDotPlot.gnu
echo plot \""$nom14.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom15.png\" >> scriptDotPlot.gnu
echo plot \""$nom15.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom16.png\" >> scriptDotPlot.gnu
echo plot \""$nom16.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo set output \"$nom17.png\" >> scriptDotPlot.gnu
echo plot \""$nom17.dat"\" using 1:2 with dots >> scriptDotPlot.gnu
echo "Execution du script gnuplot"

gnuplot scriptDotPlot.gnu

echo "Fin du script"