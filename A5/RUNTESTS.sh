#!/bin/sh
# Pass in a 488 compiler
COMPILER="java -jar dist/compiler488.jar"
FLAGS="-D aby"
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
    for file in $(ls src/compiler488/testing/); do
        echo "======================================"
        echo "testing passing for $file"
        cat src/compiler488/testing/$file
        $COMPILER src/compiler488/testing/$file
        echo "======================================"
    done
fi


