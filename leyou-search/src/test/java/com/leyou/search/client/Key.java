package com.leyou.search.client;

public class Key {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public Key(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null || !(o instanceof Key)){
              return false;
        }else {
            return this.getId().equals(((Key) o).getId());
        }
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
