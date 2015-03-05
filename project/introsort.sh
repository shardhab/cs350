#!/bin/bash -x

for j in 500T 1M 5M 10M 15M 20M 25M 30M; do
  for i in `seq 1 25`; do 
    java -cp heapsort heapsort $j heapsort/$j.out
  done
done
