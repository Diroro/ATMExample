package AutomatedTellerMachine;

import Denomination.Denomination;
import Exceptions.InvalidValueException;

import java.util.Map;

public interface ATMInterface {
    void deposit(int value, Denomination denomination);

    int getBalance();

    Map<Denomination, Integer> withdrawCash(int value) throws InvalidValueException;
}
