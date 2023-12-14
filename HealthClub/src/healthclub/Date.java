/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthclub;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate date;

    // Constructors
    public Date() {
        this.date = LocalDate.now();
    }

    public Date(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    // Getter and setter methods
    public LocalDate getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
    }

    // Display date as a string
    public String toString() {
        return date.toString();
    }
}
