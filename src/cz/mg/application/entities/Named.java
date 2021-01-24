package cz.mg.application.entities;

import cz.mg.collections.text.Text;


public interface Named extends cz.mg.collections.text.Named {
    @Override
    Text getName();
}
