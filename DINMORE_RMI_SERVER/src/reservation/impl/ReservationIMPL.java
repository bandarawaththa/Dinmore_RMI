package reservation.impl;

import java.util.HashMap;

public class ReservationIMPL<T> {
    HashMap<Object, T> map = new HashMap<>();

    public boolean reserved(Object key, T service) {
        if (map.containsKey(key)) {
            return map.get(key)==service;
        }else{
            map.put(key, service);
            return true;
        }
    }

    public boolean released(Object key,T service){
        if (map.containsKey(key) && map.get(key)==service) {
            map.remove(key);
            return true;
        }
        return false;
    }
}