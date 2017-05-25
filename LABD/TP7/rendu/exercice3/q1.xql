xquery version "1.0";

(: Generated with EditiX at Sun Mar 19 18:23:58 CET 2017 :)

<CATALOG>
	{for $p in doc("plant_catalog.xml")//PLANT
	 for $f in doc("plant_families.xml")//FAMILY[SPECIES = $p/BOTANICAL]
			return 
			<PLANT>
			{$p/COMMON}
			{$p/BOTANICAL}
			{$p/ZONE}
			{$p/LIGHT}
			{$p/PRICE}
			{$p/AVAILABILITY}
			<FAMILY>{$f/NAME/text()}</FAMILY>
			</PLANT>
		}
</CATALOG>		

