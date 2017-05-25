xquery version "1.0";

(: Generated with EditiX at Wed Mar 29 21:38:28 CEST 2017 :)

import module namespace local =  "http://www.expression.org" at "expression.xq";

declare option saxon:output "omit-xml-declaration=yes";

let $expression := "expression.xml"
let $variables := "variables.xml"

return local:eval-var($expression,$variables)
