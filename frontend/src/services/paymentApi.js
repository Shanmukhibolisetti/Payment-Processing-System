export async function makePayment(amount, method) {
  const response = await fetch("http://localhost:8080/pay", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      amount: Number(amount),
      method: method
    })
  });

  if (!response.ok) {
    throw new Error("Payment failed");
  }

  return response.json();
}
