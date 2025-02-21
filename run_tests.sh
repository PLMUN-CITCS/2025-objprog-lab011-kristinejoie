#!/bin/bash

# Run the java program and test all input/output pairs in the tests folder
java_script="NumberClassifierMenu.java"

declare -A results

# Loop through all subdirectories in the tests folder (handling spaces correctly)
find tests -mindepth 1 -maxdepth 1 -type d -print0 | while IFS= read -r -d $'\0' test_dir; do
  test_name=$(basename "$test_dir")
  input_file="$test_dir/input.txt"
  output_file="$test_dir/output.txt"

  echo "Running test: $test_name"

  # Initialize test_result for each test
  test_result="" # Important: Initialize here!

  # Check if input and output files exist (robustly)
  if (! test -f "$input_file" ); then  # Corrected: Spaces around! and -f
    echo "Error: Input file '$input_file' not found."
    test_result="MISSING INPUT"
  elif (! test -f "$output_file" ); then # Corrected: Spaces around! and -f
    echo "Error: Output file '$output_file' not found."
    test_result="MISSING OUTPUT"
  else
    # Run the java program
    output=$(java "$java_script" < "$input_file" 2>&1)
    return_code=$?

    if [ $return_code -ne 0 ]; then
      echo "java program crashed for test $test_name:"
      echo "$output"
      test_result="CRASHED"
      exit 1
    else
      diff -Bw <(cat "$output_file") <(echo "$output") > diff.txt
      if [ -s diff.txt ]; then
        echo "Test $test_name failed:"
        echo "Diff:"
        cat diff.txt
        test_result="FAIL"
        rm diff.txt  # Clean up (only if it exists)
        exit 1
      else
        test_result="PASS"
      fi
      rm diff.txt  # Clean up (only if it exists)
    fi
  fi

    echo "Test $test_name: $test_result"
    echo "------------------------------------"

  # Store the result in an associative array (more efficient for counting)
  results[$test_name]="$test_result"

done

exit 0
