package AutomatedTellerMachine;

import Denomination.Denomination;
import Exceptions.InvalidValueException;

import java.util.HashMap;
import java.util.Map;

public class AutomatedTellerMachine implements ATMInterface {
    private Map<Denomination, Integer> cash = createEmptyCashBox();

    public void deposit(int value, Denomination denomination) {
        cash.put(denomination, value);
    }

    public int getBalance() {
        return cash.entrySet()
                .stream()
                .mapToInt((entrySet) -> entrySet.getValue() * entrySet.getKey().getNoteValue())
                .sum();
    }

    private Map<Denomination, Integer> createEmptyCashBox() {
        Map<Denomination, Integer> cashBox = new HashMap<>();
        for (Denomination denomination : Denomination.getValues()) {
            cashBox.put(denomination, 0);
        }
        return cashBox;
    }

    public Map<Denomination, Integer> withdrawCash(int value) throws InvalidValueException {
        if (value > getBalance()) {
            throw new InvalidValueException("Insufficient funds!");
        }
        Map<Denomination, Integer> withdrawal = createEmptyCashBox();
        int remain = value;

        for (Denomination denomination : Denomination.getValues()) {
            int needNotes = remain / denomination.getNoteValue();
            if (needNotes != 0) {
                var existNotes = cash.get(denomination);
                int gotNotes;
                if (existNotes >= needNotes) {
                    gotNotes = needNotes;
                    cash.put(denomination, existNotes - needNotes);
                } else {
                    gotNotes = existNotes;
                    cash.put(denomination, 0);
                }
                remain -= gotNotes * denomination.getNoteValue();
                withdrawal.put(denomination, gotNotes);

                if (remain == 0) {
                    break;
                }
            }

        }

        if (remain > 0) {
            throw new InvalidValueException("Can't get needed value!");
        }
        return withdrawal;
    }
}

