package Assignment4;

public class suffixRadixMsd {




    static <T> void insertionSort(T[] input, GetDigitHandle<T> gbh, int d, int arrStart, int arrEndInclusive){


        for (int i = arrStart+1; i<=arrEndInclusive; i++){

            int j=i;

           while (j>arrStart &&
                   isPrevBiggerThanNextNew(input[j-1],input[j],gbh,d)){

                T temp = input[j-1];
                input[j-1] = input[j];
                input[j] = temp;
                j--;
            }
       }
    }
    

    static <T> boolean isPrevBiggerThanNextNew(T prev, T next, GetDigitHandle<T> gbh, int d){


        while (gbh.getDigit(prev, d) == gbh.getDigit(next, d)) {

            d++;

            if (gbh.getDigit(prev,d) == -1 &&
                    gbh.getDigit(next,d) == -1){

                return false;
            }
        }

        return gbh.getDigit(prev, d) > gbh.getDigit(next, d);
    }


    //d = den position i sträng vi e i
    static  <T> void  Sort(T[]input, GetDigitHandle<T>gbh, int alphabetSize, T[] aux, int lo, int hi, int d){


        if (hi-lo <500){
            insertionSort(input,gbh,d,lo,hi);
            return;
        }

        int[] count = new int[alphabetSize+2]; //+2 för o ha plats för -1 i getdigit

        //frequency
        for (int i=lo; i <= hi; i++){
            int digit = gbh.getDigit(input[i],d);
            count[digit+2] += 1;
        }

        //cumulatives
        for (int r=0; r<alphabetSize+1;r++){
            count[r+1] += count[r];
        }

        //
        for (int i = lo; i<=hi; i++){
            int digit = gbh.getDigit(input[i],d)+1; //hämtar värdet för digit
            int position = count[digit]; //hämtar nuvarande position som ska skrivas till
            aux[position] = input[i];
            count[digit]++;
        }


        for (int i=lo; i<=hi; i++){
            input[i] = aux[i-lo];
        }

        //inkl hi
        for (int r=0; r<alphabetSize; r++){
            Sort(input,gbh,alphabetSize,aux,lo+count[r],lo+count[r+1]-1,d+1);
        }




    }


    static <T> void MsdRadixSort(T[]input, GetDigitHandle<T>gbh, int alphabetSize) {

        T[] aux = input.clone();
        Sort(input,gbh,alphabetSize,aux,0,input.length-1,0);

    }




    static GetDigitHandle<String> getChar = new GetDigitHandle<String>() {
        public int getDigit(String elem, int i) {

            if (i < elem.length()) {
                return elem.charAt(i);
            } else {
                return -1;
            }
        }
    };


    interface GetDigitHandle<T> {
        int getDigit(T elem, int i);
    }



}
