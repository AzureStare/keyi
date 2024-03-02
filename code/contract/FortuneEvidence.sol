// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

contract FortuneEvidence {

    struct Fortune {
        string name;
        string birth;
        string description;
        string fortune;
        string ipfsHash;
    }
    event EvidenceSaved(address addr, string name, string birth, string description, string fortune, string ipfsHash);

    mapping(address => Fortune) public fortuneMap;
    mapping(address => bool) private register;

    function saveEvidence(string memory name, string memory birth, string memory description, string memory fortune, string memory ipfsHash) public{
        fortuneMap[msg.sender] = Fortune(name, birth, description, fortune, ipfsHash);
        register[msg.sender] = true;
        emit EvidenceSaved(msg.sender, name, birth, description, fortune, ipfsHash);
    }

    function isRegister(address addr) public view returns(bool) {
        return register[addr];
    }

    function getBirthDay(address addr) public view returns(string memory) {
        require(register[addr], "address is not register");
        return fortuneMap[addr].birth;
    }

    function getfortune(address addr) public view returns(string memory) {
        require(register[addr], "address is not register");
        return fortuneMap[addr].fortune;
    }

    function getfortuneHash(address addr) public view returns(string memory) {
        require(register[addr], "address is not register");
        return fortuneMap[addr].ipfsHash;
    }

}