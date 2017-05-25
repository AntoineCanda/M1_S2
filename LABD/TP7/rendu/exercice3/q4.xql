xquery version "1.0";

(: Generated with EditiX at Wed Mar 22 22:25:34 CET 2017 :)

<PRICE>
	{round-half-to-even( sum(  
		for $p in doc("plant_order.xml")//PLANT
			return
			(xs:double(fn:data(substring(doc("plant_catalog.xml")//PRICE[../COMMON = $p/COMMON],2)))) * fn:data($p/QUANTITY)
	),1) }
</PRICE>
