package com.appsterlight.model.domain.UI;

import jakarta.servlet.http.HttpServletRequest;

public class Pagination {
    private final Integer minPages;
    private final Integer maxPages;
    private final Integer step;
    private Integer page;
    private Integer pageSize;
    private final HttpServletRequest req;

    {
        minPages = 15;
        maxPages = 60;
        step = 15;
    }

    public Pagination(HttpServletRequest req) {
        this.req = req;
    }

    public Integer getPageSize() {
        String pageSizeStr = req.getParameter("pageSize");
        pageSize = pageSizeStr != null ? Integer.parseInt(pageSizeStr) : minPages;
        req.setAttribute("pageSize", pageSize);

        return pageSize;
    }

    public void setPage(Integer oldPageSize) {
        String pageStr = req.getParameter("page");
        page = (pageStr == null || pageSize != oldPageSize) ? 1: Integer.parseInt(pageStr);
        req.setAttribute("page", page);
    }

    public Integer getOffset() {
        return (page - 1) * pageSize;
    }

    public Integer setPagesCount(int totalItems) {
        int pagesCount = (int) Math.ceil((double) totalItems / pageSize);
        req.setAttribute("pagesCount", pagesCount);

        return pagesCount;
    }

    public void setPagesAttributes() {
        req.setAttribute("minPages", minPages);
        req.setAttribute("maxPages", maxPages);
        req.setAttribute("step", step);
    }

}
