
package com.cydeo.pojo.readyPojo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Countries {

    private List<Item> items;
    private Boolean hasMore;
    private Integer limit;
    private Integer offset;
    private Integer count;
    private List<Link__1> links;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Link__1> getLinks() {
        return links;
    }

    public void setLinks(List<Link__1> links) {
        this.links = links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
