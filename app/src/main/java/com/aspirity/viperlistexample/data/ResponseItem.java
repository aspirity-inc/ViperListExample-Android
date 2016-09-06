package com.aspirity.viperlistexample.data;

import java.util.List;

/**
 * Created by namtarr on 06.09.16.
 */

public class ResponseItem {

    private List<Item> list;
    private Pager pager;

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public class Pager {
        private int page;
        private int pages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }
}
