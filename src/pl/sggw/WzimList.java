package pl.sggw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class WzimList<E> implements java.util.List<E> {
    private int index = 0;
    private E[] elements;

    public WzimList(){
        elements = (E[]) new Object[Integer.MAX_VALUE/16];
        //elements = new Object[100];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null){
                if ((elements[i]).equals(o)) {
                    result = true;
                    break;
                }
            } else  {
                if (elements[i] == o) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int next = 0;
            @Override
            public boolean hasNext() {
                return next < index;
            }

            @Override
            public Object next() {
                Object result = null;
                if (next < index) {
                    result = elements[next];
                    next++;
                }
                return result;
                //return elements[next++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] tmp = new Object[index];
        for (int i = 0; i < index; i++) {
            tmp[i] = elements[i];
        }
        //System.arraycopy(elements, 0, tmp, 0, index);
        return tmp;
    }

    @Override
    public boolean add(Object o) {
        /*
        if (o == null)
            throw new NullPointerException();
         */
        boolean result = false;
        if (index < elements.length) {
            elements[index++] = (E) o;
            result = true;
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        int tmp = 0;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null){
                if ((elements[i]).equals(o)) {
                    tmp = i;
                    result = true;
                    break;
                }
            } else  {
                if (elements[i] == o) {
                    tmp = i;
                    result = true;
                    break;
                }
            }
        }
        if (result){
            for (int i = tmp; i < index; i++) {
                elements[i] = elements[i+1];
            }
            //System.arraycopy(elements, tmp + 1, elements, tmp, index - tmp);
            elements[index--] = null;
        }
        return result;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean result = false;
        if (c.size() < 1)
            return result;
        result = true;
        Object[] tmp = c.toArray();
        /*
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == null)
                throw new NullPointerException();
        }
        */
        for (int i = 0; i < tmp.length; i++) {
            elements[index++] = (E) tmp[i];
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        boolean result = false;
        if (index < 0 || index > this.index)
            throw new IndexOutOfBoundsException();
        if (c.size() < 1) {
            return result;
        }
        result = true;
        Object[] tmp = c.toArray();
        /*
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == null)
                throw new NullPointerException();
        }
         */
        Object[] oldElements = new Object[elements.length];
        int counter = 0;
        for (int i = index; i < this.index; i++) {
            oldElements[counter++] = elements[i];
        }
        counter = 0;
        for (int i = index; i < tmp.length+index; i++) {
            elements[i] = (E) tmp[counter++];
            this.index++;
        }
        counter = 0;
        for (int i = index+tmp.length; i < this.index; i++) {
            elements[i] = (E) oldElements[counter++];
        }
        return result;
    }

    @Override
    public void clear() {
        elements = (E[]) new Object[Integer.MAX_VALUE/16];
        //elements = new Object[100];
        index = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > this.index)
            throw new IndexOutOfBoundsException();
        return elements[index];
    }

    @Override
    public E set(int index, Object element) {
        if (index < 0 || index > this.index)
            throw new IndexOutOfBoundsException();
        E result = elements[index];
        elements[index] = (E) element;
        return result;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > this.index)
            throw new IndexOutOfBoundsException();
        for (int i = this.index; i >= index; i--) {
            elements[i+1] = elements[i];
        }
        //if (this.index + 1 - index >= 0) System.arraycopy(elements, index, elements, index + 1, this.index + 1 - index);
        elements[index] = (E) element;
        this.index++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > this.index)
            throw new IndexOutOfBoundsException();
        E result = elements[index];
        for (int i = index; i < this.index; i++) {
            elements[i] = elements[i+1];
        }
        //System.arraycopy(elements, index + 1, elements, index, this.index - index);
        this.index--;
        //elements[this.index--] = null;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        int result = -1;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null){
                if ((elements[i]).equals(o)) {
                    result = i;
                    break;
                }
            }  else  {
                if (elements[i] == o) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        int result = -1;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null){
                if ((elements[i]).equals(o)) {
                    result = i;
                }
            }  else  {
                if (elements[i] == o) {
                    result = i;
                }
            }
        }
        return result;
    }

    @Override
    public ListIterator listIterator() {
        return new ListIterator() {
            private int ind = 0;
            boolean next = false;
            boolean previous = false;

            @Override
            public boolean hasNext() {
                return ind < index;
            }

            @Override
            public Object next() {
                Object result = null;
                if (ind < index) {
                    result = elements[ind];
                    ind++;
                }
                next=true;
                return result;
                //return elements[ind++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public Object previous() {
                Object result = null;
                if (ind >= 0) {
                    result = elements[ind];
                    ind--;
                }
                previous = true;
                return result;
                //return elements[ind--];
            }

            @Override
            public int nextIndex() {
                return ind + 1;
                }

            @Override
            public int previousIndex() {
                return ind - 1;
            }

            @Override
            public void remove() {
                if (next || previous) {
                    for (int i = ind; i < index; i++) {
                        elements[i] = elements[i+1];
                    }
                    ind--;
                    index--;
                    next = previous = false;
                }
            }

            @Override
            public void set(Object o) {
                if (next || previous) {
                    elements[ind] = (E) o;
                    next = previous = false;
                }
            }

            @Override
            public void add(Object o) {
                for (int i = index; i >= ind; i--) {
                    elements[i+1] = elements[i];
                }
                elements[ind] = (E) o;
                index++;
            }
        };
    }

    @Override
    public ListIterator listIterator(int i) {
        return new ListIterator() {
            private int ind = i;
            boolean next = false;
            boolean previous = false;

            @Override
            public boolean hasNext() {
                return ind < index;
            }

            @Override
            public Object next() {
                Object result = null;
                if (ind < index) {
                    result = elements[ind];
                    ind++;
                }
                next=true;
                return result;
                //return elements[ind++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public Object previous() {
                Object result = null;
                if (ind >= 0) {
                    result = elements[ind];
                    ind--;
                }
                previous = true;
                return result;
                //return elements[ind--];
            }

            @Override
            public int nextIndex() {
                return ind + 1;
            }

            @Override
            public int previousIndex() {
                return ind - 1;
            }

            @Override
            public void remove() {
                if (next || previous) {
                    for (int i = ind; i < index; i++) {
                        elements[i] = elements[i+1];
                    }
                    ind--;
                    index--;
                    next = previous = false;
                }
            }

            @Override
            public void set(Object o) {
                if (next || previous) {
                    elements[ind] = (E) o;
                    next = previous = false;
                }
            }

            @Override
            public void add(Object o) {
                for (int i = index; i >= ind; i--) {
                    elements[i+1] = elements[i];
                }
                elements[ind] = (E) o;
                index++;
            }
        };
    }

    @Override
    public java.util.List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > index || fromIndex > toIndex || toIndex > index)
            throw new IndexOutOfBoundsException();
        java.util.List<Object> result = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            result.add(elements[i]);
        }
        //result.addAll(Arrays.asList(elements).subList(fromIndex, toIndex));
        return result;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean[] tests = new boolean[index];
        boolean result = false;
        Object[] param = c.toArray();
        for (int i = 0; i < index; i++) {
            for (Object o : param) {
                if (elements[i] != null) {
                    if ((elements[i]).equals(o)) {
                        tests[i] = true;
                        break;
                    }
                } else {
                    if (elements[i] == o) {
                        tests[i] = true;
                        break;
                    }
                }
            }
        }
        int[] tmp = new int[tests.length];
        for (int i = 0; i < tmp.length; i++) {
            if (!tests[i]) {
                tmp[i] = i;
            } else {
                tmp[i] = -1;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] >= 0){
                result = true;
                for (int j = tmp[i]; j < this.index; j++) {
                    elements[j] = elements[j+1];
                }
                //if (this.index - tmp[i] >= 0) System.arraycopy(elements, tmp[i] + 1, elements, tmp[i], this.index - tmp[i]);
                this.index--;
                //elements[this.index--] = null;
                for (int j = i; j < tmp.length; j++) {
                    tmp[j]--;
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null)
            throw new NullPointerException();
        boolean result = false;
        Object[] param = c.toArray();
        boolean[] tests = new boolean[index];
        for (int i = 0; i < index; i++) {
            for (Object o : param) {
                if (elements[i] != null) {
                    if ((elements[i]).equals(o)) {
                        tests[i] = true;
                        break;
                    }
                } else {
                    if (elements[i] == o) {
                        tests[i] = true;
                        break;
                    }
                }
            }
        }
        int[] tmp = new int[tests.length];
        for (int i = 0; i < tmp.length; i++) {
            if (tests[i]) {
                tmp[i] = i;
            } else {
                tmp[i] = -1;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] >= 0){
                result = true;
                for (int j = tmp[i]; j < this.index; j++) {
                    elements[j] = elements[j+1];
                }
                //if (this.index - tmp[i] >= 0) System.arraycopy(elements, tmp[i] + 1, elements, tmp[i], this.index - tmp[i]);
                this.index--;
                //elements[this.index--] = null;
                for (int j = i; j < tmp.length; j++) {
                    tmp[j]--;
                }
            }
        }
        /*
        for (int i = 0; i < param.length; i++) {
            while (this.remove(param[i])) {result = true;}
        }
        */
        return result;
    }

    @Override
    public boolean containsAll(Collection c) {
        if (c == null)
            throw new NullPointerException();
        boolean result = true;
        boolean[] tests = new boolean[c.size()];
        Object[] param = c.toArray();
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < param.length; j++) {
                if (elements[i] != null){
                    if ((elements[i]).equals(param[j])) {
                        tests[j] = true;
                        break;
                    }
                } else  {
                    if (elements[i] == param[j]) {
                        tests[j] = true;
                        break;
                    }
                }
            }
        }
        for (boolean test : tests) {
            if (!test) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a == null)
            throw new NullPointerException();
        int size = index;
        if (size < a.length)
            size = a.length;
        Object[] tmp = new Object[size];
        for (int i = 0; i < index; i++) {
            tmp[i] = elements[i];
        }
        //if (index >= 0) System.arraycopy(elements, 0, tmp, 0, index);
        return tmp;
    }
}
