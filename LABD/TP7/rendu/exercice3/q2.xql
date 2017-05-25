xquery version "1.0";

(: Generated with EditiX at Sun Mar 19 18:58:57 CET 2017 :)

<CATALOG>
	{ for $l in distinct-values(doc("plant_catalog.xml")//LIGHT)
	return 
	<LIGHT>
		<EXPOSURE>{fn:data($l)}</EXPOSURE>
		{for $p in doc("plant_catalog.xml")//PLANT
		where $p/LIGHT = $l
		return
		<PLANT>
			{$p/COMMON}
			{$p/BOTANICAL}
			{$p/ZONE}
			{$p/PRICE}
			{$p/AVAILABILITY}
		</PLANT>
		}
	</LIGHT>		
	}
</CATALOG>	
