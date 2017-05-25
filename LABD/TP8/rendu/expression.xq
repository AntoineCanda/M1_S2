xquery version "1.0";

(: Generated with EditiX at Thu Mar 23 10:50:08 CET 2017 :)


module namespace local =  "http://www.expression.org";

declare default element namespace "http://www.expression.org";

declare option saxon:output "omit-xml-declaration=yes";

declare function local:print($name as xs:string) as xs:string
{
	concat(local:print_rec(doc($name)/expr/op),'')	
};

declare function local:print_rec($node as node()){
	if($node/name() = 'op') then
		concat('(',local:print_rec($node/*[1]),$node/@val,local:print_rec($node/*[2]),')')
	else($node)
};


declare function local:eval($name as xs:string) as xs:integer {
	local:eval_rec(doc($name)/expr/op)
};


declare function local:eval_rec($name as node()){
	
	if($name/name() = 'op') then
		if($name/@val='/') then
			xs:integer(local:eval_rec($name/*[1]) div local:eval_rec($name/*[2]))
		else(
			if($name/@val='*') then
				xs:integer(local:eval_rec($name/*[1]) * local:eval_rec($name/*[2]))
			else(
				if($name/@val='+') then
					xs:integer(local:eval_rec($name/*[1]) + local:eval_rec($name/*[2]))
				else(
					if($name/@val='-') then
						xs:integer(local:eval_rec($name/*[1]) - local:eval_rec($name/*[2]))
					else( 
					)
				)
			)
		)
		
	else (
		if($name/name() = 'var') then
			fn:error(xs:QName("Erreur"),"NaN",$name)
		else($name)
	)
};

declare function local:eval-var($name as xs:string, $variables as xs:string) as xs:integer {
	local:eval_var_rec(doc($name)/expr/op, doc($variables))
};

declare function local:eval_var_rec($name as node(), $variables){

	if($name/name() = 'op') then
		if($name/@val='/') then
			xs:integer(local:eval_var_rec($name/*[1],$variables) div local:eval_var_rec($name/*[2],$variables))
		else(
			if($name/@val='*') then
				xs:integer(local:eval_var_rec($name/*[1],$variables) * local:eval_var_rec($name/*[2],$variables))
			else(
				if($name/@val='+') then
					xs:integer(local:eval_var_rec($name/*[1],$variables) + local:eval_var_rec($name/*[2],$variables))
				else(
					if($name/@val='-') then
						xs:integer(local:eval_var_rec($name/*[1],$variables) - local:eval_var_rec($name/*[2],$variables))
					else( 
					)
				)
			)
		)
		
	else (
		if($name/name() = 'var') then
			if(count($variables//*[./name() = $name]) > 1) then
				fn:error(xs:QName("Erreur"),"Variable déclarée plusieurs fois",$name)
			else(
				if(count($variables//*[./name() = $name]) < 1) then
					fn:error(xs:QName("Erreur"),"Variable non définie",$name)
				else (
					$variables//*[./name()=$name]
				)
			)
		else($name)
	)
};


