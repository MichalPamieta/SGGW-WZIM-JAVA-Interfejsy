package pl.sggw;

import java.util.Collection;
import java.util.Iterator;

public class WzimSet<E> implements java.util.Set<E> {

    private int index = 0;
    private E[] elements;

    public WzimSet() {
        elements = (E[]) new Object[Integer.MAX_VALUE / 16];
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
            if (elements[i] != null) {
                if ((elements[i]).equals(o)) {
                    result = true;
                    break;
                }
            } else {
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
        boolean result = true;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null) {
                if ((elements[i]).equals(o)) {
                    result = false;
                    break;
                }
            } else {
                if (elements[i] == o) {
                    result = false;
                    break;
                }
            }
        }
        if (result && index < elements.length) {
            elements[index++] = (E) o;
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        int tmp = 0;
        for (int i = 0; i < index; i++) {
            if (elements[i] != null) {
                if ((elements[i]).equals(o)) {
                    tmp = i;
                    result = true;
                    break;
                }
            } else {
                if (elements[i] == o) {
                    tmp = i;
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            for (int i = tmp; i < index; i++) {
                elements[i] = elements[i + 1];
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
        Object[] param = c.toArray();
        /*
        for (int i = 0; i < param.length; i++) {
            if (param[i] == null)
                throw new NullPointerException();
        }
        */
        boolean[] tests = new boolean[c.size()];
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < param.length; j++) {
                if (elements[i] != null) {
                    if ((elements[i]).equals(param[j])) {
                        tests[j] = true;
                        break;
                    }
                } else {
                    if (elements[i] == param[j]) {
                        tests[j] = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < tests.length; i++) {
            if (!tests[i] && index < elements.length) {
                result = true;
                elements[index++] = (E) param[i];
            }
        }
        return result;
    }

    @Override
    public void clear() {
        index = 0;
        elements = (E[]) new Object[Integer.MAX_VALUE / 16];
        //elements = new Object[100];
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
