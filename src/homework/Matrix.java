package homework;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    /**
     * 外层表示行
     */
    private List<List<Integer>> values;

    /**
     * 若干构造方法
     * @param row
     * @param col
     */
    public Matrix(int row, int col){
        //这里不能直接获取，不支持不安全的访问
        values = new ArrayList<>(row);
        for(int i = 0; i < row; i++) {
            values.add(new ArrayList<>());
        }
    }
    public Matrix (Matrix matrix){
        for(List<Integer> list: matrix.getValues()){
            List<Integer> element = new ArrayList<>();
            for(int e : list){
                element.add(e);
            }
            values.add(element);
        }
    }
    public Matrix (int[][] valuesOut){
        for(int[] row: valuesOut){
            List<Integer> element = new ArrayList<>();
            for(int e : row){
                element.add(e);
            }
            values.add(element);
        }
    }
    //这里一般不会复制，都是直接访问实际地成员变量,注意这里实现的是把
    //具体地数组上的存的值返回，有时候是值，有时候是地址，不会把存值的地址返回
    public List<List<Integer>> getValues(){
        return values;
    }
    /**
     * 验证是否合法
     */
    public boolean check(int row, int col){
        return (row >= 0 && row <= values.size()) && (col >= 0 && col <= values.get(0).size());
    }
    /**
     * 获取查询方法
     */
    public int getByPosition(int row, int col){
        if(check(row, col)){
            return values.get(row).get(col);
        }
        return Integer.MIN_VALUE;
    }

    public List getByRow(int row){
        if(check(row, 0)){
            return values.get(row);
        }
        return null;
    }

    public List getByCol(int col) {
        if(check(0, col)){
            List<Integer> ans = new ArrayList<>();
            for(int i = 0; i < values.size(); i++) {
                ans.add(values.get(i).get(col));
            }
            return ans;
        }
        return null;
    }
    /**
     * 添加方法
     */

    public void addByRow(List<Integer> element, int row){
        if(check(row, 0)){
            values.add(row, element);
        }
    }

    public void addByCol(List<Integer> element, int col) {
        if(check(0, col)){
            for(int i = 0; i < values.size(); i++) {
                values.get(i).add(col, element.get(i));
            }
        }
    }
    /**
     * 修改方法
     */
    public void update(int row, int col, int value){
        if(check(row, col)){
            values.get(row).set(col, value);
        }
    }
    /**
     * 删除方法
     */
    public void deleteByRow(int row){
        if(check(row, 0)){
            values.remove(row);
        }
    }

    public void deleteByCol(int col){
        if(check(0, col)){
            for(int i = 0 ; i < values.size(); i++) {
                values.get(i).remove(col);
            }
        }
    }
}
