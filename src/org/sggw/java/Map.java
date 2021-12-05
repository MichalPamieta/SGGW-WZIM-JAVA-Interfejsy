package org.sggw.java;

import java.util.Collection;


public class Map<K, V> implements java.util.Map<K, V> {

    private int index = 0;
    private K[] keys;
    private V[] values;


    public Map() {
        keys = (K[]) new Object[Integer.MAX_VALUE / 16];
        //keys = (K[]) new Object[100];
        values = (V[]) new Object[Integer.MAX_VALUE / 16];
        //values = (V[]) new Object[100];
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
    public boolean containsKey(Object key) {
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (keys[i] != null) {
                if ((keys[i]).equals(key)) {
                    result = true;
                    break;
                }
            } else {
                if (keys[i] == key) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (values[i] != null) {
                if ((values[i]).equals(value)) {
                    result = true;
                    break;
                }
            } else {
                if (values[i] == value) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public V get(Object key) {
        V result = null;
        int test = -1;
        for (int i = 0; i < index; i++) {
            if (keys[i] != null) {
                if ((keys[i]).equals(key)) {
                    test = i;
                    break;
                }
            } else {
                if (keys[i] == key) {
                    test = i;
                    break;
                }
            }
        }
        if(test != -1) {
            result = values[test];
        }
        return result;
    }

    @Override
    public V put(Object key, Object value) {
        V result = null;
        int test = -1;
        for (int i = 0; i < index; i++) {
            if (keys[i] != null) {
                if ((keys[i]).equals(key)) {
                    test = i;
                    break;
                }
            } else {
                if (keys[i] == key) {
                    test = i;
                    break;
                }
            }
        }
        if(test != -1) {
            result = values[test];
            values[test] = (V) value;
        } else {
            keys[index] = (K) key;
            values[index] = (V) value;
            index++;
        }
        return result;
    }

    @Override
    public V remove(Object key) {
        V result = null;
        int test = -1;
        for (int i = 0; i < index; i++) {
            if (keys[i] != null) {
                if ((keys[i]).equals(key)) {
                    test = i;
                    break;
                }
            } else {
                if (keys[i] == key) {
                    test = i;
                    break;
                }
            }
        }
        if (test != -1 ) {
            result = values[test];
            for (int i = test; i < index; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            keys[index] = null;
            values[index] = null;
            index--;
        }
        return result;
    }

    @Override
    public void putAll(java.util.Map m) {
        Object[] param = m.keySet().toArray();
        /*
        for (int i = 0; i < param.length; i++) {
            if (param[i] == null)
                throw new NullPointerException();
        }
        */
        boolean[] tests = new boolean[m.size()];
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < param.length; j++) {
                if (keys[i] != null) {
                    if ((keys[i]).equals(param[j])) {
                        tests[j] = true;
                        break;
                    }
                } else {
                    if (keys[i] == param[j]) {
                        tests[j] = true;
                        break;
                    }
                }
            }
        }
        Object[] mKeys = m.keySet().toArray();
        Object[] mValues = m.values().toArray();
        for (int i = 0; i < tests.length; i++) {
            if (!tests[i] && index < keys.length) {
                keys[index] = (K) mKeys[i];
                values[index] = (V) mValues[i];
                index++;
            }
        }
    }

    @Override
    public void clear() {
        keys = (K[]) new Object[Integer.MAX_VALUE / 16];
        //keys = (K[]) new Object[100];
        values = (V[]) new Object[Integer.MAX_VALUE / 16];
        //values = (V[]) new Object[100];
        index = 0;
    }

    @Override
    public Set keySet() {
        Set<K> result = new Set<>();
        for (K key : keys) {
            result.add(key);
        }
        //Collections.addAll(result, keys);
        return result;
    }

    @Override
    public Collection values() {
        List<V> result = new List<>();
        for (V value : values) {
            result.add(value);
        }
        //result.addAll(Arrays.asList(values));
        return result;
    }

    @Override
    public Set entrySet() {
        Set<Entry> result = new Set<>();
        for (int i = 0; i < index; i++) {
            result.add("key: " + keys[i] + " value: " + values[i]);
        }
        return  result;
    }
}
