#!/bin/bash -x

for k in heapsort mergesort quicksort dual_pivot_qs; do
  touch $k/total.avg
  for j in 1T 5T 10T 50T 100T 500T 1M 5M 10M; do
    cat $k/$j.out.avg >> $k/total.avg 
  done
done
