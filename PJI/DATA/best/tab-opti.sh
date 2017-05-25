RESULT_FILE="./result.csv"
TOP_NUMBER="10"

function header(){
    echo "\begin{figure}[h!]"
    echo "\centering"
    echo "\begin{tabular}{|l|l|l|l|l|l|l|}"
    echo "\hline"
    echo "Conv1 & Conv2 & ConvSz & fonction & nbFull & convergence & précision\\\\"
}

function footer(){
    echo "\hline"
    echo "\end{tabular}"
    echo "\end{figure}"
}

function transform(){
    cut -d '/' -f 3 | sed -e "s/opti-//g" | sed -e "s/-/\&/g" | sed -e "s/.data//g" | sed -e "s/;/\&/g" |  sed -e "s/$/\\\\\\\\/g" | sed -e "i\\\\\hline"
}

header
sort -k 5 -t '.' result.csv -g -r | head -n $TOP_NUMBER | transform
footer


header
sort -k 2 -t ';' $RESULT_FILE -g | head -n $TOP_NUMBER | transform
footer
