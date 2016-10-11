package org.gooin.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //  显示数量
    private void displayQuantity(int num) {
        TextView mTextView = (TextView) findViewById(R.id.quantity_text_view);
        mTextView.setText("" + num);
    }

    /**
     * 提交订单按钮,显示订单信息
     *
     * @param view
     */
    public void submitOrder(View view) {
//        加奶油
        CheckBox creamCheckBox = (CheckBox) findViewById(R.id.cream_check_box);
        boolean hasWhippedCream = creamCheckBox.isChecked();

//        加巧克力
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

//        输入姓名
        EditText editText = (EditText) findViewById(R.id.edit_text_name);
        String name = editText.getText().toString();

//        计算价格
        int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);
//        调用函数创建订单
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(priceMessage);

    }

    //   数量增加 1
    public void increment(View view) {

        if (quantity == 100) {
            Toast.makeText(this, "没有那么多的咖啡啦~", Toast.LENGTH_SHORT).show();
            return ;
        }
        quantity++;
        displayQuantity(quantity);
    }

    //     数量减少 1
    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(this, "至少买一杯哦~", Toast.LENGTH_SHORT).show();
            return ;
        }
        quantity--;
        displayQuantity(quantity);

    }

    //    显示提示消息,数量,价格等
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * 计算价格
     *
     * @param quantity 咖啡数量
     * @param hasWhippedCream  是否加奶
     * @param hasChocolate      是否加巧克力
     * @return      总价
     */
    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;

        //加奶 1$
        if (hasWhippedCream) {
            price = price + 1;
            Log.i(TAG, "calculatePrice: cream " + price);
        }

        // 加巧克力 2$
        if (hasChocolate) {
            price = price + 2;
            Log.i(TAG, "calculatePrice: chocolate " + price);
        }
        price = quantity * price;

        return price;
    }

    /**
     * 创建订单汇总信息
     *
     * @param price           of caffee
     * @param addWhippedCream 加奶油
     * @param addChocolate    加巧克力
     * @param name            of customer
     * @return
     */

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd whipped Cream ? " + addWhippedCream;
        priceMessage += "\nAdd Chocloate ? " + addChocolate;
        priceMessage += "\nQuantity is :" + quantity;
        priceMessage += "\nTotal is : $" + price;
        priceMessage += "\nThank you!";
        return (priceMessage);
    }


}
