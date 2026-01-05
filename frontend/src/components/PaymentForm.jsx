import { useState } from "react";
import { makePayment } from "../services/paymentApi";

function PaymentForm() {
    const [amount, setAmount] = useState(0);
    const [mode, setMode] = useState("UPI");
    const [result, setResult] = useState("");

    const handleRequest = async () => {
        if(amount==0) {
            alert("Please enter the amount!");
            return;
        }
        try {
            const data = await makePayment(amount, mode);
            setResult(data.message);
        }
        catch {
            setResult("Failed");
        }

    }

    return (
        <div className="form">
            <h3>Payment Processing System</h3>

            <input type="number" placeholder="Enter amount" value={amount} onChange={(e) => setAmount(e.target.value)} />
            <select value={mode} onChange={(e) => setMode(e.target.value)}>
                <option value="UPI">UPI</option>
                <option value="Card">Card</option>
                <option value="Net_Banking">Net Banking</option>
            </select>
            <button onClick={handleRequest}>Pay</button>
            {result && <p>{result}</p>}
        </div>
    );
}

export default PaymentForm;