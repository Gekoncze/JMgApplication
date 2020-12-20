package cz.mg.application;

import cz.mg.collections.text.Text;


public interface Named extends cz.mg.collections.text.Named {
    @Override
    Text getName();
}
