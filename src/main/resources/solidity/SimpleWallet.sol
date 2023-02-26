pragma solidity ^0.6.0;

contract SimpleWallet {

    mapping(address => uint256) balances;
    address owner;

    event LogDeposit(address indexed sender, uint amount);
    event LogWithdrawal(address indexed receiver, uint amount);
    event LogTransfer(address indexed sender, address indexed receiver, uint amount);

    constructor() public {
        owner = msg.sender;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "Only contract owner can perform this action");
        _;
    }

    function deposit() external payable {
        balances[msg.sender] += msg.value;
        emit LogDeposit(msg.sender, msg.value);
    }

    function withdraw(uint256 amount) external {
        require(balances[msg.sender] >= amount, "Insufficient balance");
        balances[msg.sender] -= amount;
        payable(msg.sender).transfer(amount);
        emit LogWithdrawal(msg.sender, amount);
    }

    function transfer(address receiver, uint256 amount) external {
        require(balances[msg.sender] >= amount, "Insufficient balance");
        balances[msg.sender] -= amount;
        balances[receiver] += amount;
        emit LogTransfer(msg.sender, receiver, amount);
    }

    function getBalance() external view returns (uint256) {
        return balances[msg.sender];
    }

    function getOwner() public view returns (address) {
        return owner;
    }

    function destroy() public onlyOwner {
        selfdestruct(payable(owner));
    }
}