
public class MySet implements MyMath<MySet> {
	
	private int[] setArray;
	
	
	public MySet(int[] arr) {
		setArray = removeDuplicates(arr);
	}
	
	private int[] removeDuplicates(int[] arr) {
		int end = arr.length;
		for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (arr[i] == arr[j]) {                  
                	
                    for(int shiftLeft = j; shiftLeft < end-1; shiftLeft++) {
                        arr[shiftLeft] = arr[shiftLeft+1];
                    }

                    end--;
                    j--;
                }
            }
        }

        int[] newArr = new int[end];

        for (int i = 0; i < end; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
		
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int set : setArray)
			s.append(set + " ");
		return " " + s; 
	}
	
	@Override
	public MySet add(MySet o) {
		int total = setArray.length + o.getSetArray().length;
		int [] join = new int[total];
		for (int i = 0; i < setArray.length; i++) {
			join[i] = setArray[i];
		}
		
		for (int i = setArray.length; i < total; i++) {
			join[i] = o.getSetArray()[i - setArray.length];
		}
		int [] arr = removeDuplicates(join);
		return new MySet(arr);
	}
	
	@Override
	public MySet subtract(MySet o) {
		int[] comp = o.getSetArray();
		int arrValue = setArray.length + o.getSetArray().length;
		int[] hold = new int[arrValue];
		int count = 0;
		for (int i = 0; i < setArray.length; i++) {
			boolean unique = true;
			for (int j = 0; j < comp.length; j++) {
				if (setArray[i] == comp[j]) {
					unique = false;
				} 
			}
				if (unique == true){
					hold[count] = setArray[i];
					count++;
				}
		}
		int[] newArr = new int[count];
		for (int i = 0; i < count; i++) {
			newArr[i] = hold[i];
		}
		return new MySet(newArr);
	}
	
	@Override
	public MySet multiply(MySet o) {
		int[] symm = o.getSetArray();
		int arrValue = setArray.length + o.getSetArray().length;
		int[] hold = new int[arrValue];
		int count = 0;
		for (int i = 0; i < setArray.length; i++) {
			boolean unique = true;
			for (int j = 0; j < symm.length; j++) {
				if (setArray[i] == symm[j]) {
					unique = false;
				} 
			}
				if (unique == true){
					hold[count] = setArray[i];
					count++;
				}
		}
		for (int i = 0; i < symm.length; i++) {
			boolean unique = true;
			for (int j = 0; j < setArray.length; j++) {
				if (symm[i] == setArray[j]) {
					unique = false;
				} 
			}
				if (unique == true){
					hold[count] = symm[i];
					count++;
				}
		}
		
		int[] newArr = new int[count];
		for (int i = 0; i < count; i++) {
			newArr[i] = hold[i];
		}
		return new MySet(newArr);
	}
	
	@Override
	public MySet divide(MySet o) {
		int[] inter = o.getSetArray();
		int arrValue = setArray.length + o.getSetArray().length;
		int[] hold = new int[arrValue];
		int count = 0;
		for (int i = 0; i < setArray.length; i++) {
			boolean intersects = false;
			for (int j = 0; j < inter.length; j++) {
				if (setArray[i] == inter[j]) {
					intersects = true;
				} 
			}
				if (intersects == true){
					hold[count] = setArray[i];
					count++;
				}
		}
		
		int[] newArr = new int[count];
		for (int i = 0; i < count; i++) {
			newArr[i] = hold[i];
		}
		return new MySet(newArr);
	}
	
	

	public int[] getSetArray() {
		return setArray;
	}
	
	public void setSetArray(int[] arr) {
		this.setArray = removeDuplicates(arr);
	}

	
	
}
