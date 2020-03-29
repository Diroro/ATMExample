import AutomatedTellerMachine.AutomatedTellerMachine;
import Denomination.Denomination;
import Exceptions.InvalidValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class Main {
    private AutomatedTellerMachine atm;

    @Before
    public void before() {
        atm = new AutomatedTellerMachine();
    }

    @Test
    public void getBalance() {
        int value = 5;
        atm.deposit(value, Denomination.ONE);
        final var balance = atm.getBalance();
        assertEquals(value, balance);
    }

    @Test
    public void checkDifferentDenominations() {
        atm.deposit(1, Denomination.ONE);
        atm.deposit(1, Denomination.TWO);
        atm.deposit(1, Denomination.TWENTY);
        atm.deposit(2, Denomination.FIFTY);
        atm.deposit(2, Denomination.HUNDRED);

        final var balance = atm.getBalance();
        assertEquals(balance, 323);
    }

    @Test
    public void successWithdrawValue() {
        putTenOfAllDenominations();
        Map<Denomination, Integer> withdrawal = atm.withdrawCash(488);
        int[] expectedValues = {4, 1, 1, 1, 1, 1, 1};
        int[] gotValues = withdrawalToArray(withdrawal);
        assertArrayEquals(gotValues, expectedValues);
    }

    @Test
    public void successWithdrawValueMoreThanThousand() {
        putTenOfAllDenominations();
        Map<Denomination, Integer> withdrawal = atm.withdrawCash(1288);
        int[] expectedValues = {10, 5, 1, 1, 1, 1, 1};
        int[] gotValues = withdrawalToArray(withdrawal);
        assertArrayEquals(gotValues, expectedValues);
    }

    @Test
    public void notAllDenominationsAreNeeded() {
        putTenOfAllDenominations();
        Map<Denomination, Integer> withdrawal = atm.withdrawCash(1000);
        int[] gotValues = withdrawalToArray(withdrawal);
        int[] expectedValues = {10, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(gotValues, expectedValues);
    }


    @Test
    public void insufficientFunds() {
        putTenOfAllDenominations();
        Exception exception = assertThrows(InvalidValueException.class, () -> {
            Map<Denomination, Integer> withdrawal = atm.withdrawCash(2000);
        });

        assertEquals(exception.getMessage(), "Insufficient funds!");
    }

    @Test
    public void cantGetAllDenominations() {
        atm.deposit(10, Denomination.HUNDRED);
        Exception exception = assertThrows(InvalidValueException.class, () -> {
            Map<Denomination, Integer> withdrawal = atm.withdrawCash(120);
        });
        assertEquals(exception.getMessage(), "Can't get needed value!");

    }


    private int[] withdrawalToArray(Map<Denomination, Integer> withdrawal) {
        return new int[]{
                withdrawal.get(Denomination.HUNDRED),
                withdrawal.get(Denomination.FIFTY),
                withdrawal.get(Denomination.TWENTY),
                withdrawal.get(Denomination.TEN),
                withdrawal.get(Denomination.FIVE),
                withdrawal.get(Denomination.TWO),
                withdrawal.get(Denomination.ONE),
        };
    }

    private void putTenOfAllDenominations() {
        if (atm == null) {
            atm = new AutomatedTellerMachine();
        }
        for (Denomination denomination : Denomination.getValues()) {
            atm.deposit(10, denomination);
        }
    }
}
