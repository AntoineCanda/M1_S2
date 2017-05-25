xquery version "1.0";

(: Generated with EditiX at Wed Mar 29 22:29:40 CEST 2017 :)

import module namespace local =  "http://www.expression.org" at "expression.xq";

declare option saxon:output "omit-xml-declaration=yes";

let $expression := "expression5.xml"

return local:print($expression)
