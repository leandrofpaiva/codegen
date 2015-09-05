package br.com.codegen.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateVO {

    private String className;

    private String packageName;

    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
        this.list = list;
    }
}
