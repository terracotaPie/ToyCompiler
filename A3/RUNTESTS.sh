# Pass in a 488 compiler
COMPILER=$1
FLAGS=""
TEST_DIRS="failing passing"

echo "Testing SS0-54 with compiler $COMPILER"

mkdir tests/results


for file in $(ls tests/passing); do
  echo "testing passing for $file"
  $COMPILER $file > tests/results/$file.passing.test
done
for file in $(ls tests/failing); do
  echo "testing failing for $file"
  $COMPILER $file > tests/results/$file.failing.test
done

