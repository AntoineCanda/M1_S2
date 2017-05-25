DATA_DIRECTORY="../data_arch2_2"
SCRIPT_NAME="best.R"
THRESHOLD="0.0025"
RESULT_FILE="./result.csv"
TOP_NUMBER="10"

rm -f $RESULT_FILE

for file in $(ls $DATA_DIRECTORY)
do
    Rscript $SCRIPT_NAME $DATA_DIRECTORY/$file $THRESHOLD >> $RESULT_FILE
done

echo "faster convergence"
sort -k 2 -t ';' $RESULT_FILE -g | head -n $TOP_NUMBER
echo "best precision"
sort -k 5 -t '.' result.csv -g -r | head -n $TOP_NUMBER

echo "smaller convergence"
sort -k 2 -t ';' $RESULT_FILE -g -r | head -n $TOP_NUMBER
echo "worst precision"
sort -k 5 -t '.' result.csv -g | head -n $TOP_NUMBER
