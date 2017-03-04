# Pass in a 488 compiler
COMPILER="java -jar dist/compiler488.jar"
FLAGS="-X -D aby"
TEST_DIRS="failing passing"

echo "Testing SS0-54 with compiler $COMPILER"

mkdir tests/results
mkdir tests/results/passing
mkdir tests/results/failing

if [ $# -eq 2 ]
then
  $COMPILER $FLAGS $1 > $2
elif [ $# -eq 1 ]
then
  $COMPILER $FLAGS $1
else
  for file in $(ls tests/passing); do
    echo "testing passing for $file"
    $COMPILER $FLAGS tests/passing/$file > tests/results/passing/$file.results
  done
  for file in $(ls tests/failing); do
    echo "testing failing for $file"
    $COMPILER $FLAGS tests/failing/$file > tests/results/failing/$file.results
  done
fi


