xquery version "1.0";

(: Generated with EditiX at Sun Mar 19 19:27:38 CET 2017 :)

declare variable $doc_families := "plant_families.xml";
<CATALOG>
	{ for $li in distinct-values( doc("plant_catalog.xml")//LIGHT)
	order by $li ascending
	return 
	<LIGHT>
		<EXPOSURE>{fn:data($li)}</EXPOSURE>
		{for $p in doc("plant_catalog.xml")//PLANT
		where $p/LIGHT = $li
		order by $p/COMMON ascending
		return
		<PLANT>
			{$p/COMMON}
			{$p/BOTANICAL}
			{$p/ZONE}
			{$p/PRICE}
			{$p/AVAILABILITY}
			<FAMILY>{fn:data(doc($doc_families)//SPECIES[.=$p/BOTANICAL]/../NAME)} </FAMILY>
		</PLANT>
		}
	</LIGHT>		
	}
</CATALOG>	
