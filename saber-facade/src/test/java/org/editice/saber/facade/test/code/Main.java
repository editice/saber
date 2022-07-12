package org.editice.saber.facade.test.code;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 银行账户N个，期望确保每个账户余额>=100，求为了实现该目标的转账操作
 *
 * 示例输入：["a",80],["b",180],["c",90]
 * 输出：["b","a",20],["b","c",10]
 *
 *
 * @author tinglang
 * @date 2022/7/7.
 */
public class Main {
    private static final Long MINIMAL_AMOUNT = 100L;

    public static void main(String[] args) {
        System.out.println("Hello LeetCoder");

        Main main = new Main();
        main.testCase();

    }

    @Test
    public void testCase(){
        BankAccount account1 = new BankAccount("a", 80);
        BankAccount account2 = new BankAccount("b", 180);
        BankAccount account3 = new BankAccount("c", 90);

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        List<Transfer> transfers = makeTransfers(accounts);
        System.out.println(transfers);
    }


    private static List<Transfer> makeTransfers(List<BankAccount> accounts) {
        Collections.sort(accounts);
        int len = accounts.size();
        long totalAmount = 0L;
        for (BankAccount account : accounts) {
            totalAmount += account.amount;
        }

        if (totalAmount < len * MINIMAL_AMOUNT) {
            throw new RuntimeException("total amount less than minimal amount!, please xxx");
        }

        List<Transfer> result = new ArrayList<>();
        long res = 0L;
        int j = len - 1;
        for (int i = 0; i < len; i++) {
            BankAccount tmp = accounts.get(i);
            if (tmp.amount >= MINIMAL_AMOUNT) {
                return result;
            }


            long gap = MINIMAL_AMOUNT - tmp.amount;
            while (gap > 0) {
                long maxTransfer = Math.min(gap, accounts.get(j).amount - MINIMAL_AMOUNT);
                accounts.get(j).amount -= maxTransfer;
                tmp.amount += maxTransfer;
                gap -= maxTransfer;
                if(maxTransfer<gap) {
                    j--;
                }

                result.add(new Transfer(accounts.get(j).accountName, tmp.accountName, maxTransfer));
            }
        }

        return result;
    }


    static class Transfer {
        String fromAccountName;
        String toAccountName;
        long transferAmount;

        public Transfer(String fromAccountName, String toAccountName, long amount) {
            this.fromAccountName = fromAccountName;
            this.toAccountName = toAccountName;
            this.transferAmount = amount;
        }

        @Override
        public String toString() {
            return "Transfer{" +
                    "fromAccountName='" + fromAccountName + '\'' +
                    ", toAccountName='" + toAccountName + '\'' +
                    ", transferAmount=" + transferAmount +
                    '}';
        }
    }

    static class BankAccount implements Comparable<BankAccount> {
        String accountName;
        long amount;

        public BankAccount(String accountName, long amount) {
            this.accountName = accountName;
            this.amount = amount;
        }

        @Override
        public int compareTo(BankAccount o) {
            return Long.compare(this.amount, o.amount);
        }
    }


}
