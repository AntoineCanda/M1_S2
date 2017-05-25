xquery version "1.0";

(: Generated with EditiX at Wed Mar 29 20:53:09 CEST 2017 :)

import module namespace local =  "http://www.expression.org" at "expression.xq";

declare option saxon:output "omit-xml-declaration=yes";
let $expression := "expression5.xml"

return local:eval($expression)
